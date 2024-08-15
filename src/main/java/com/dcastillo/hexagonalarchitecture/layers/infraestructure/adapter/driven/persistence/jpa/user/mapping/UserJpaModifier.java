package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.mapping;

import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.entity.UserJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Setter
public class UserJpaModifier {
    private final RoleJpaMapper roleJpaMapper;

    private User domainUser;

    public UserJpaModifier applyChangesFrom(User user) {
        final UserJpaModifier userJpaMapper = new UserJpaModifier(roleJpaMapper);
        userJpaMapper.setDomainUser(domainUser);
        return userJpaMapper;
    }

    public UserJpaEntity to(final UserJpaEntity userEntity) {
        userEntity.setId(domainUser.getUserId().asUUID());
        userEntity.setEmailAddress(domainUser.getEmailAddress().value());
        userEntity.setUsername(domainUser.getUsername());
        userEntity.setPassword(domainUser.getPassword());
        userEntity.setRole(roleJpaMapper.toJpaEntity(domainUser.getRole()));

        return userEntity;
    }
}
