package com.samples;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

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

    @Bean
    public DataSource dataSource(){
        DataSource dataSource = new BasicDataSource();
        ((BasicDataSource) dataSource).setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        ((BasicDataSource) dataSource).setUrl("jdbc:derby://localhost:1527/testdb");
        ((BasicDataSource) dataSource).setMaxTotal(10);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public CircleDao3 circleDao3() {
        CircleDao3 circleDao3 = new CircleDao3();
        circleDao3.setDataSource(dataSource());
        return circleDao3;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.getHibernateProperties().setProperty("dialect", "org.hibernate.dialect.DerbyDialect");
        return (SessionFactory) sessionFactory.getObject();
    }

}
