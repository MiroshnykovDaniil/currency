package com.example.currency.config;


import com.example.currency.api.CurrencyApi;
import com.example.currency.view.ConvertView;
import com.example.currency.view.RatesView;
import com.example.currency.view.TabsView;
import com.example.currency.view.MainView;
import com.example.currency.viewmodel.ConvertViewModel;
import com.example.currency.viewmodel.RatesViewModel;
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
    public MainView viewModel(){
        return new MainView();
    }

    @Bean
    public TabsView tabsController(){return new TabsView();}

    @Bean
    public ConvertView convertController(){return new ConvertView();}

    @Bean
    public RatesView ratesController(){return new RatesView();}

    @Bean
    public RatesViewModel ratesViewModel(){return new RatesViewModel();}

    @Bean
    public CurrencyApi currencyApi(){return new CurrencyApi();}

    @Bean
    public ConvertViewModel convertViewModel(){return new ConvertViewModel();}
}
