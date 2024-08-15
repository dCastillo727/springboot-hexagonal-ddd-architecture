package com.dcastillo.hexagonalarchitecture.layers.infraestructure.configuration;

import com.dcastillo.hexagonalarchitecture.layers.domain.utilities.PasswordEncryptor;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.providers.encryptors.BcryptPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncryptor passwordEncryptor() {
        return new BcryptPasswordEncryptor();
    }
}
