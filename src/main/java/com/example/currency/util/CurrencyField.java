package com.example.currency.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import com.example.currency.api.CurrencyApi;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TextField;

public class CurrencyField extends TextField{

    private NumberFormat format;
    //private SimpleDoubleProperty amount = new SimpleDoubleProperty(this, "amount", 1.00);

    private SimpleObjectProperty<BigDecimal> amount = new SimpleObjectProperty<BigDecimal>(this,"amount",new BigDecimal(1.00));

    public CurrencyField(){
    }

    public CurrencyField(Currency currency, BigDecimal initialAmount) {
        setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        amount = new SimpleObjectProperty<BigDecimal>(this, "amount", initialAmount);
        format = NumberFormat.getCurrencyInstance();
        format.setCurrency(currency);

        setText(format.format(initialAmount));

        // Remove selection when textfield gets focus
        focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Platform.runLater(() -> {
                int lenght = getText().length();
                selectRange(lenght, lenght);
                positionCaret(lenght);
            });
        });

        // Listen the text's changes
        textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                formatText(newValue);
            }
        });
    }

    /**
     * Get the current amount value
     * @return Total amount
     */
    public BigDecimal getAmount() {
        return amount.get();
    }

    /**
     * Property getter
     * @return SimpleDoubleProperty
     */
    public SimpleObjectProperty<BigDecimal> amountProperty() {
        return this.amount;
    }

    /**
     * Change the current amount value
     * @param newAmount
     */
    public void setAmount(BigDecimal newAmount) {
        if(newAmount.compareTo(BigDecimal.ZERO) >= 0) {
            amount.set(newAmount);
            formatText(format.format(newAmount));
        }
    }

    /**
     * Set Currency format
     * @param currency
     */
    public void setCurrencyFormat(Currency currency) {
        //format = NumberFormat.getCurrencyInstance(new Locale(currency.getCurrencyCode()));
        format.setCurrency(currency);
        //format.setCurrency(currency);
        formatText(format.format(getAmount()));
    }

    private void formatText(String text) {
        if(text != null && !text.isEmpty()) {
            String plainText = text.replaceAll("[^0-9]", "");

            while(plainText.length() < 3) {
                plainText = "0" + plainText;
            }

            StringBuilder builder = new StringBuilder(plainText);
            builder.insert(plainText.length() - 2, ".");

            BigDecimal newValue = new BigDecimal(builder.toString());
            amount.set(newValue);
            setText(format.format(newValue));
        }
    }

    @Override
    public void deleteText(int start, int end) {
        StringBuilder builder = new StringBuilder(getText());
        builder.delete(start, end);
        formatText(builder.toString());
        selectRange(start, start);
    }

}
