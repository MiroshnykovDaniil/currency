package com.example.currency.config;


import com.example.currency.view.TabsController;
import com.example.currency.view.ViewController;
import javafx.stage.Stage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    @Bean
    public ViewController viewModel(){
        return new ViewController();
    }

    @Bean
    public TabsController tabsController(){return new TabsController();}
}
