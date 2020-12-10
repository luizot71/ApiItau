package com.itau.br.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableCaching
@PropertySource(value = "classpath:message.properties")
@EnableAutoConfiguration
@ComponentScan({"com.itau.br.app"})
public class TransferApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(TransferApplication.class, args);
    }
}
