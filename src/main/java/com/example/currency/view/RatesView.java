package com.example.currency.view;

import com.example.currency.viewmodel.RatesViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Currency;
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
    private TableView<?> ratesTableView;

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
    }

}
