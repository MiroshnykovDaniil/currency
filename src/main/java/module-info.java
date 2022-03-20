open module com.example.currency {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires io.reactivex.rxjava3;
    requires java.net.http;
    requires org.json;
    // opens com.example.currency to javafx.fxml;
    exports com.example.currency;

  //  opens com.example.currency.view to javafx.fxml;


    exports com.example.currency.view;
}