package com.example.currency.view;


import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class TabsController {

    @FXML
    private TabPane TabPane;

    @FXML
    private Tab ConvertTab;

    @FXML
    private Tab CurrencyTab;


}
