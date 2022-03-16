package com.example.currency.config;


import com.example.currency.view.ViewController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ViewController viewModel(){
        return new ViewController();
    }
}
