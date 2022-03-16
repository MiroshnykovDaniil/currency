package com.example.currency.view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewController {

    @FXML
    @Autowired
    TabsController tabsController;

    @FXML
    private Label welcomeText;

    @FXML
    protected TabsController onHelloButtonClick() {
        return tabsController;
    }


}
