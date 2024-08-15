package com.dcastillo.hexagonalarchitecture.layers.infraestructure.providers.encryptors;

import com.dcastillo.hexagonalarchitecture.layers.domain.utilities.PasswordEncryptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordEncryptor implements PasswordEncryptor {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BcryptPasswordEncryptor() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encrypt(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public boolean matches(String password, String encryptedPassword) {
        return bCryptPasswordEncoder.matches(password, encryptedPassword);
    }
}
