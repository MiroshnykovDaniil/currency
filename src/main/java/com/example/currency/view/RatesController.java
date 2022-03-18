package com.example.currency.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

@Component
public class RatesController {


    @FXML
    private ComboBox<?> currencyCombobox;

    @FXML
    private Label currencyLabel;

    @FXML
    private Label ratesLabel;

    @FXML
    private TableView<?> ratesTableView;

}
