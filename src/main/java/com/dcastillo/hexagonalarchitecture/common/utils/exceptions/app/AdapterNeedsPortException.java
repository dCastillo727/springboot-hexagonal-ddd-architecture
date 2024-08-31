package com.dcastillo.hexagonalarchitecture.common.utils.exceptions.app;

public class AdapterNeedsPortException extends RuntimeException {
    public AdapterNeedsPortException(String className) {
        super("Error on class " + className + ": Class annotated as adapter needs a matching interface annotated as port");
    }
}
