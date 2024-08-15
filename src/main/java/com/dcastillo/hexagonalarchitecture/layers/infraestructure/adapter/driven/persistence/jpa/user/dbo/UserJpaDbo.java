package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.dbo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserJpaDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email_address", unique = true, nullable = false)
    private String emailAddress;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleJpaDbo role;
}
