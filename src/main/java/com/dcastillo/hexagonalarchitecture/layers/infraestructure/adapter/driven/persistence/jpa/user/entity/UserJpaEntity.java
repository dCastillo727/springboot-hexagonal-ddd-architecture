package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserJpaEntity implements Serializable {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "email_address", unique = true, nullable = false)
    private String emailAddress;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private RoleJpaEntity role;
}
