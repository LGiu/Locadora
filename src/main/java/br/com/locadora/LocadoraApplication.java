package br.com.locadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LocadoraApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocadoraApplication.class, args);
    }
}
