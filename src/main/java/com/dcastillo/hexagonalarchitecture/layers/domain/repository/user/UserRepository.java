package com.dcastillo.hexagonalarchitecture.layers.domain.repository.user;

import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.EmailAddress;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.UserId;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User changeUserInfo(User user);

    List<User> findAllBy(
            //TODO add filter
    );

    void deleteBy(UserId userId);

    Optional<User> findById(UserId userId);

    Optional<User> findByEmailAddress(EmailAddress email);

    Optional<User> findByUsername(String username);

    void save(User user);
}
