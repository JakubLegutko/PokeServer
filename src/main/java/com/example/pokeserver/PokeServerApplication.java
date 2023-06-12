package com.example.pokeserver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class PokeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PokeServerApplication.class, args);
    }
}
