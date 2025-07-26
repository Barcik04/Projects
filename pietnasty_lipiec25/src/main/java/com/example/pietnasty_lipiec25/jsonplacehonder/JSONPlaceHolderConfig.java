package com.example.pietnasty_lipiec25.jsonplacehonder;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class JSONPlaceHolderConfig {
    @Bean
    JsonPlaceHolderService jsonPlaceHolderService() {
        RestClient restClient =  RestClient.create("https://jsonplaceholder.typicode.com");
        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build()
                .createClient(JsonPlaceHolderService.class);
    }
}
