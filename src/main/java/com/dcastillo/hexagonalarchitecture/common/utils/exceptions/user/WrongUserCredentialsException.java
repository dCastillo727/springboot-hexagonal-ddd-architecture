package com.dcastillo.hexagonalarchitecture.common.utils.exceptions.user;

public class WrongUserCredentialsException extends RuntimeException {
    public WrongUserCredentialsException(String message) {
        super(message);
    }
}
