package com.example.pokeserver;

import com.example.pokeserver.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(RsaKeyProperties.class)
public class PokeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PokeServerApplication.class, args);
    }
}
