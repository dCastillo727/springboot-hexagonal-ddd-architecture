package com.dcastillo.hexagonalarchitecture.layers.application.core.user.service;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DrivenAdapter;
import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DriverAdapter;
import com.dcastillo.hexagonalarchitecture.common.utils.exceptions.user.UserAlreadyExistsException;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driven.messaging.LoggerPublisher;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driven.persistence.repository.user.UserRepositoryDrivenPort;
import com.dcastillo.hexagonalarchitecture.layers.application.core.user.usecase.UserRegisterUseCase;
import com.dcastillo.hexagonalarchitecture.layers.domain.command.user.RegisterUserCommand;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import com.dcastillo.hexagonalarchitecture.layers.domain.utilities.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
@RequiredArgsConstructor
public class UserService implements UserRegisterUseCase {
    /**
     * Because only the port implementations that are marked as {@link DrivenAdapter} or {@link DriverAdapter}
     * can be loaded as bean you can have more than 1 port implementation prepared to be loaded
     * and switch which has the annotation, in case you have 2 annotations
     * for a same port the usual autowire error will occur
     */
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final UserRepositoryDrivenPort repository;
    private final PasswordEncryptor passwordEncryptor;
    private final LoggerPublisher loggerPublisher;

    @Override
    public User registerUser(RegisterUserCommand command) {
        Optional<User> userOptional = repository.findByUsername(command.username());

        if (userOptional.isPresent())
            throw new UserAlreadyExistsException("Username already exists");

        userOptional = repository.findByEmailAddress(command.emailAddress());

        if (userOptional.isPresent())
            throw new UserAlreadyExistsException("Email address already exists");

        final User user = User.registerBy(command);
        user.setPassword(passwordEncryptor.encrypt(command.password()));
        repository.save(user);

        loggerPublisher.publish(user.listEvents());
        user.clearEvents();

        return user;
    }
}
