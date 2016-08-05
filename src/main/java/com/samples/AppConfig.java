package com.samples;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by edara on 8/4/16.
 */
@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public Connection connection() {
        Connection connection = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/testdb");

        }catch(Exception ex) {
            System.out.println("Error creating connection");
            System.exit(9);
        }
        return connection;
    }


}
