package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.dbo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
public enum RoleJpaDbo {
    USER("user"),
    ADMIN("admin"),
    MANAGER("manager");
    ;
    private final String dbRepresentation;

    public static RoleJpaDbo from(String value) {
        Objects.requireNonNull(value);

        for (final RoleJpaDbo role : RoleJpaDbo.values()) {
            if (role.dbRepresentation.equalsIgnoreCase(value)) return role;
        }

        throw new IllegalArgumentException("Invalid role: " + value);
    }
}
