package com.zed.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Configuration
public class SocialConfig {

    public static final String USER_ID = "evgeniy.zachateyskiy";

    @Value("${facebook.app.id}")
    private String facebookAppId;

    @Value("${facebook.app.secret}")
    private String facebookAppSecret;

    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();

        registry.addConnectionFactory(new FacebookConnectionFactory(facebookAppId,
                "facebookAppSecret"));

        return registry;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository() {
        return usersConnectionRepository().createConnectionRepository(USER_ID);
    }

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.databaseurl}")
    private String databaseurl;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public UsersConnectionRepository usersConnectionRepository() {
        // JDBC DataSource pointing to the DB where connection data is stored
        DriverManagerDataSource dataSource = new DriverManagerDataSource(databaseurl, username, password);
        dataSource.setDriverClassName(driverClassName);


// encryptor of connection authorization credentials
        TextEncryptor encryptor = new TextEncryptor() {
            public String encrypt(String text) {
                return text;
            }

            public String decrypt(String encryptedText) {
                return encryptedText;
            }
        };

        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), encryptor);
    }

    @Bean
    public ConnectController connectController() {
        return new ConnectController(connectionFactoryLocator(),
                connectionRepository());
    }


}