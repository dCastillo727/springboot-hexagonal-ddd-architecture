package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DrivenAdapter;
import com.dcastillo.hexagonalarchitecture.common.utils.exceptions.user.UserNotFoundException;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.EmailAddress;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.UserId;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driven.persistence.repository.user.UserRepositoryDrivenPort;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.entity.UserJpaEntity;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.mapping.UserJpaMapper;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.persistence.jpa.user.mapping.UserJpaModifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
@DrivenAdapter
@RequiredArgsConstructor
public class UserRepositoryDrivenAdapter implements UserRepositoryDrivenPort {
    private final UserJpaMapper userJpaMapper;
    private final UserJpaModifier userJpaModifier;

    private final JpaRepositoryUser jpaRepository;

    @Override
    public User changeUserInfo(User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");

        final UserJpaEntity foundUser = jpaRepository
                .findById(user.getUserId().asUUID())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + user.getUserId().asString()));

        final UserJpaEntity modifiedUser = userJpaModifier.applyChangesFrom(user).to(foundUser);
        jpaRepository.save(modifiedUser);

        return user;
    }

    @Override
    public List<User> findAllBy() {
        //TODO implement later
        return List.of();
    }

    @Override
    public void deleteBy(UserId userId) {
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");

        jpaRepository.deleteById(userId.asUUID());
    }

    @Override
    public Optional<User> findById(UserId userId) {
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");

        return jpaRepository.findById(userId.asUUID()).map(userJpaMapper::toDomainModel);
    }

    @Override
    public Optional<User> findByEmailAddress(EmailAddress email) {
        if (email == null) throw new IllegalArgumentException("Email address cannot be null");

        return jpaRepository.findByEmailAddress(email.value()).map(userJpaMapper::toDomainModel);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        if (username == null || username.isEmpty()) throw new IllegalArgumentException("Username cannot be null or empty");

        return jpaRepository.findByUsername(username).map(userJpaMapper::toDomainModel);
    }

    @Override
    public void save(User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");

        final UserJpaEntity entity = userJpaMapper.toJpaEntity(user);
        jpaRepository.save(entity);
    }
}
