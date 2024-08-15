package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user;

import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.dbo.UserJpaDbo;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional(isolation = Isolation.READ_COMMITTED)
public interface JpaRepositoryUser extends Repository<UserJpaDbo, UUID> {
    void deleteById(UUID id);

    Optional<UserJpaDbo> findById(UUID id);

    Optional<UserJpaDbo> findByUsername(String username);

    Optional<UserJpaDbo> findByEmailAddress(String emailAddress);

    //TODO add pageable list with specification filter
}
