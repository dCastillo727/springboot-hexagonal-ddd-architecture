package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user;

import com.dcastillo.hexagonalarchitecture.layers.application.port.driven.persistence.repository.user.UserRepositoryDrivenPort;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.EmailAddress;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
//@DrivenAdapter
@RequiredArgsConstructor
public class NotAnnotatedAdapterDoesntLoadAsBean implements UserRepositoryDrivenPort {
    @Override
    public User changeUserInfo(User user) {
        return null;
    }

    @Override
    public List<User> findAllBy() {
        return List.of();
    }

    @Override
    public void deleteBy(UserId userId) {

    }

    @Override
    public Optional<User> findById(UserId userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAddress(EmailAddress email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void save(User user) {

    }
}
