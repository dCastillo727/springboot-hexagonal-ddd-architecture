package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.mapping;

import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.EmailAddress;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.UserId;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.entity.UserJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserJpaMapper {
    private final RoleJpaMapper roleJpaMapper;

    public User toDomainModel(UserJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        return User.from(
                UserId.from(jpaEntity.getId().toString()),
                EmailAddress.from(jpaEntity.getEmailAddress()),
                jpaEntity.getUsername(),
                jpaEntity.getPassword(),
                roleJpaMapper.toDomainModel(jpaEntity.getRole())
        );
    }

    public UserJpaEntity toJpaEntity(User domainUser) {
        if (domainUser == null) return null;

        final UserJpaEntity jpaEntity = new UserJpaEntity();

        jpaEntity.setId(domainUser.getUserId().asUUID());
        jpaEntity.setEmailAddress(domainUser.getEmailAddress().value());
        jpaEntity.setUsername(domainUser.getUsername());
        jpaEntity.setPassword(domainUser.getPassword());
        jpaEntity.setRole(roleJpaMapper.toJpaEntity(domainUser.getRole()));

        return jpaEntity;
    }
}
