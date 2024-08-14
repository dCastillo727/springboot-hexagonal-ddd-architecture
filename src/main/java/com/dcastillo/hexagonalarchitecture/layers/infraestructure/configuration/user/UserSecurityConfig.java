package com.dcastillo.hexagonalarchitecture.layers.infraestructure.configuration.user;

import com.dcastillo.hexagonalarchitecture.layers.domain.utilities.PasswordEncryptor;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.providers.encryptors.BcryptPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserSecurityConfig {
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncryptor passwordEncryptor(PasswordEncoder passwordEncoder) {
        return new BcryptPasswordEncryptor((BCryptPasswordEncoder) passwordEncoder);
    }
}
