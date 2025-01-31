package com.example.csvplatform.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApiConfig {

    @Value("${api.baseUrl}")
    private String BASE_URL;

    // // Not recommended since it is in maintenance mode
    // @Bean
    // public RestTemplate restTemplate(){
    //     return new RestTemplate();
    // }

    @Bean
    public RestClient restClient(){
        return RestClient.create(BASE_URL);
    }
}
