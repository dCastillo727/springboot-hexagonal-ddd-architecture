package com.dcastillo.hexagonalarchitecture.common.utils.exceptions.app;

public class IllegalPortAdapterTypesCombination extends RuntimeException {
    public IllegalPortAdapterTypesCombination(String className) {
        super("Error on class " + className + ": Adapters should match type with their port interface, only Driven-Driven and Driver-Driver " +
                "annotation combinations are allowed");
    }
}
