package com.example.currency.view;


import com.example.currency.util.CurrencyField;
import com.example.currency.viewmodel.ConvertViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Currency;
import java.util.ResourceBundle;

@Component
public class ConvertView implements Initializable {

    @Autowired
    ConvertViewModel convertViewModel;

    @FXML
    private Button swapButton;

    @FXML
    private ComboBox<Currency> fromCurrencyComboBox;

    @FXML
    private ComboBox<Currency> toCurrencyCombobox;

    @FXML
    private Button convertButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private CurrencyField amountTextField;

    @FXML
    private Label resultLabel=new Label();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            convertViewModel.initCurrencyList();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        fromCurrencyComboBox.setItems(convertViewModel.getCurrencyList());
        toCurrencyCombobox.setItems(convertViewModel.getCurrencyList());

        fromCurrencyComboBox.getSelectionModel().select(Currency.getInstance("USD"));
        toCurrencyCombobox.getSelectionModel().select(Currency.getInstance("UAH"));

        amountTextField = new CurrencyField(fromCurrencyComboBox.getValue(), BigDecimal.ONE);

        anchorPane.getChildren().add(amountTextField);
        amountTextField.setLayoutX(37);
        amountTextField.setLayoutY(58);

        fromCurrencyComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Currency>() {

            @Override
            public void changed(ObservableValue<? extends Currency> observableValue, Currency oldValue, Currency newValue) {
                resultLabel.setText("");
                amountTextField.setCurrencyFormat(newValue);
            };
        });


        amountTextField.amountProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                resultLabel.setText("");
                System.out.println(newValue.doubleValue());
            }
        });
    }

    @FXML
    public void exchange() throws URISyntaxException, NoSuchAlgorithmException, IOException, InterruptedException, KeyManagementException {
        resultLabel.setText("");
        BigDecimal result = convertViewModel.exchange(fromCurrencyComboBox.getValue(),toCurrencyCombobox.getValue(),amountTextField.getAmount());
        resultLabel = new Label();
        resultLabel.setText(
                fromCurrencyComboBox.getValue()+" "
                +amountTextField.getAmount()+" = "
                +toCurrencyCombobox.getValue()+" "
                +result
        );
        resultLabel.setLayoutX(40);
        resultLabel.setLayoutY(100);
        anchorPane.getChildren().add(resultLabel);
    }

    @FXML
    private void handleButton(){
        resultLabel.setText("");

        Currency from = fromCurrencyComboBox.getValue();
        Currency to = toCurrencyCombobox.getValue();

        fromCurrencyComboBox.getSelectionModel().select(to);
        toCurrencyCombobox.getSelectionModel().select(from);
    }
}
