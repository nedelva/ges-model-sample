package com.globalcode.ges.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Programmatic JPA configuration for Jakarta EE and Spring Boot 3.5+ compatibility
 * Demonstrates modern JPA 3.1+ bootstrap without extensive XML configuration
 */
public class JpaConfig {
    
    private static final String PERSISTENCE_UNIT_NAME = "ges-model-pu";
    
    /**
     * Creates EntityManagerFactory programmatically with minimal configuration
     * Can be used in Jakarta EE environments or standalone applications
     */
    public static EntityManagerFactory createEntityManagerFactory() {
        Map<String, String> properties = new HashMap<>();
        
        // These would typically come from environment variables or configuration
        // properties.put("jakarta.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/gesdb");
        // properties.put("jakarta.persistence.jdbc.user", "gesuser");
        // properties.put("jakarta.persistence.jdbc.password", "gespass");
        // properties.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
        
        // Hibernate-specific properties (optional)
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.format_sql", "true");
        
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
    }
    
    /**
     * Creates EntityManagerFactory with custom properties
     * Useful for testing or different environments
     */
    public static EntityManagerFactory createEntityManagerFactory(Map<String, String> customProperties) {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, customProperties);
    }
}
