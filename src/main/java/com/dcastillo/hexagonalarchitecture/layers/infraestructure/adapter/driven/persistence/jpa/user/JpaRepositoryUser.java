package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user;

import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.entity.UserJpaEntity;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional(isolation = Isolation.READ_COMMITTED)
public interface JpaRepositoryUser extends Repository<UserJpaEntity, UUID> {
    void save(UserJpaEntity user);

    void deleteById(UUID id);

    Optional<UserJpaEntity> findById(UUID id);

    Optional<UserJpaEntity> findByUsername(String username);

    Optional<UserJpaEntity> findByEmailAddress(String emailAddress);

    //TODO add pageable list with specification filter
}
