package ru.magiccamp.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.magiccamp.project.config.AppConfig;

@EnableTransactionManagement
@SpringBootApplication
public class AppMain {

    public static void main(String[] args) {

        SpringApplication.run(AppConfig.class, args);

    }

}
