package com.example.currency.view;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.springframework.stereotype.Component;

@Component
public class TabsView {

    @FXML
    private TabPane TabPane;

    @FXML
    private Tab ConvertTab;

    @FXML
    private Tab CurrencyTab;

}
