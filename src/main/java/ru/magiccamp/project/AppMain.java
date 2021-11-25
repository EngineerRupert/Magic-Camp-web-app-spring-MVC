package ru.magiccamp.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.magiccamp.project.config.AppConfig;

@SpringBootApplication
public class AppMain {

    public static void main(String[] args) {

        SpringApplication.run(AppConfig.class, args);

    }

}
