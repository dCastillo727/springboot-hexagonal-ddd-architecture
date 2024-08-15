package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.mapping;

import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.role.Role;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.entity.RoleJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleJpaMapper {
    public Role toDomainModel(RoleJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        return switch (jpaEntity) {
            case USER -> Role.USER;
            case ADMIN -> Role.ADMIN;
            case MANAGER -> Role.MANAGER;
        };
    }

    public RoleJpaEntity toJpaEntity(Role domainRole) {
        if (domainRole == null) return null;

        return switch (domainRole) {
            case USER -> RoleJpaEntity.USER;
            case ADMIN -> RoleJpaEntity.ADMIN;
            case MANAGER -> RoleJpaEntity.MANAGER;
        };
    }
}
