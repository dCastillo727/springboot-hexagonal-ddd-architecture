package com.dcastillo.hexagonalarchitecture.layers.infraestructure.configuration.user;

import com.dcastillo.hexagonalarchitecture.layers.domain.utilities.PasswordEncryptor;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.providers.encryptors.BcryptPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncryptor passwordEncryptor() {
        return new BcryptPasswordEncryptor();
    }
}
