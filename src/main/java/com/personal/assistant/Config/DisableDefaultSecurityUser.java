package com.personal.assistant.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class DisableDefaultSecurityUser {
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> null;
    }
}
