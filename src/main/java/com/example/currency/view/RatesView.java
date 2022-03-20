package com.example.currency.view;

import com.example.currency.model.Rate;
import com.example.currency.viewmodel.RatesViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@Component
public class RatesView implements Initializable {

    @Autowired
    private RatesViewModel ratesViewModel;

    @FXML
    private ComboBox<Currency> currencyCombobox;

    @FXML
    private Label currencyLabel;

    @FXML
    private Label ratesLabel;

    @FXML
    private TableView<Rate> ratesTableView;


    @FXML
    private ComboBox<Currency> addCurrencyCombobox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ratesViewModel.initCurrencyList();
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
        currencyCombobox.setItems(ratesViewModel.getCurrencyList());
        addCurrencyCombobox.setItems(ratesViewModel.getCurrencyList());

        currencyCombobox.getSelectionModel().select(Currency.getInstance("USD"));

        TableColumn currencyTitle = new TableColumn("Currency");
        TableColumn currencyRate = new TableColumn("Rate");


        ObservableList<Rate> rates = FXCollections.observableArrayList(
                new Rate(Currency.getInstance("USD"),BigDecimal.ONE)
        );

        currencyTitle.setCellValueFactory(new PropertyValueFactory<Rate,Currency>("currency"));
        currencyRate.setCellValueFactory(new PropertyValueFactory<Rate,BigDecimal>("rate"));

        ratesTableView.getColumns().addAll(currencyTitle,currencyRate);
        ratesTableView.setItems(rates);

        addCurrencyCombobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Currency>() {

            @Override
            public void changed(ObservableValue<? extends Currency> observableValue, Currency oldValue, Currency newValue) {
                ratesTableView.getItems().add(ratesViewModel.rates.stream().filter(rate -> newValue.equals(rate.currency)).findFirst().get());
            };
        });


        currencyCombobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Currency>() {

            @Override
            public void changed(ObservableValue<? extends Currency> observableValue, Currency oldValue, Currency newValue) {
                try {
                    ratesViewModel.getRatesList(newValue);
                    ratesTableView.setItems(ratesViewModel.rates);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                }
            };
        });


    }

}
