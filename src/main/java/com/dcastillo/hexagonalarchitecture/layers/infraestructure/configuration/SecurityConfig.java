package com.dcastillo.hexagonalarchitecture.layers.infraestructure.configuration;

import com.dcastillo.hexagonalarchitecture.layers.domain.utilities.PasswordEncryptor;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.providers.authentication.AuthUserDetailsService;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.providers.encryptors.BcryptPasswordEncryptorProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthUserDetailsService authUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authenticationProvider(authenticationProvider())
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(authUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncryptor passwordEncryptor() {
        return new BcryptPasswordEncryptorProvider();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BcryptPasswordEncryptorProvider encryptor = (BcryptPasswordEncryptorProvider) passwordEncryptor();
        return encryptor.getBCryptPasswordEncoder();
    }
}
