package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
public enum RoleJpaEntity {
    USER("user"),
    ADMIN("admin"),
    MANAGER("manager");
    ;
    private final String dbRepresentation;

    public static RoleJpaEntity from(String value) {
        Objects.requireNonNull(value);

        for (final RoleJpaEntity role : RoleJpaEntity.values()) {
            if (role.dbRepresentation.equalsIgnoreCase(value)) return role;
        }

        throw new IllegalArgumentException("Invalid role: " + value);
    }
}
