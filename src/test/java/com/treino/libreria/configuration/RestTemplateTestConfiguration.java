package com.treino.libreria.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;

@Configuration
public class RestTemplateTestConfiguration {

    @Bean
    @Primary
    public RestTemplate restTemplateBuilderMethod() {
        return mock(RestTemplate.class);
    }

}
