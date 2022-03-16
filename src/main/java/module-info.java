open module com.example.currency {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.context;
    requires spring.core;
    requires spring.beans;


   // opens com.example.currency to javafx.fxml;
    exports com.example.currency;

  //  opens com.example.currency.view to javafx.fxml;


    exports com.example.currency.view;
}