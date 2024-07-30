package com.example.serchrepo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Configuration
public class WebClientConfig {

   @Bean
   public WebClient gitClient() {
       return WebClient.builder()
               .baseUrl("https://github.com/massgitter/spring-boot-micro-service")
               .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
               .build();
   }
}
