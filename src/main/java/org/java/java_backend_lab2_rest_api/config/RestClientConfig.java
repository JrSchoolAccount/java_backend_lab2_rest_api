package org.java.java_backend_lab2_rest_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestClient;

@Configuration
@EnableRetry
public class RestClientConfig {

    @Bean
    RestClient restClient(){
        return RestClient.create();
    }
}
