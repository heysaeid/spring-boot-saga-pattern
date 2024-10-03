package com.saga_choreography.payment.infrastracture.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;

public class DatabaseConfig {
    @Bean
    public CommandLineRunner testDatabaseConnection(DataSource dataSource) {
        return args -> {
            try (Connection conn = dataSource.getConnection()) {
                System.out.println("Database connection successful!");
                System.out.println("Database: " + conn.getMetaData().getDatabaseProductName());
                System.out.println("Version: " + conn.getMetaData().getDatabaseProductVersion());
            } catch (Exception e) {
                System.err.println("Failed to connect to the database!");
                e.printStackTrace();
            }
        };
    }
}
