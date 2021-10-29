package ru.magiccamp.project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "ru.magiccamp.project")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.magiccamp.project.dao")
public class AppConfig {
}
