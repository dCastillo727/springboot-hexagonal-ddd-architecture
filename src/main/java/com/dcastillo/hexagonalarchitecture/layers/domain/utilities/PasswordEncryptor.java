package com.dcastillo.hexagonalarchitecture.layers.domain.utilities;

public interface PasswordEncryptor {
    String encrypt(String password);

    boolean matches(String password, String encryptedPassword);
}
