package com.zed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.vkontakte.api.VKontakte;

@Configuration
public class VkontakteConfig {

    @Autowired
    ConnectionRepository connectionRepository;

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public VKontakte vKontakte() {
        return connectionRepository.getPrimaryConnection(VKontakte.class).getApi();
    }
}
