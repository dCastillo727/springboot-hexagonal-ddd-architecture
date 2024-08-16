package com.dcastillo.hexagonalarchitecture.common.utils.exceptions.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
