package com.dcastillo.hexagonalarchitecture.layers.application.service.user;

import com.dcastillo.hexagonalarchitecture.common.utils.exceptions.user.UserAlreadyExistsException;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driven.messaging.LoggerPublisher;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driven.persistence.repository.user.UserRepositoryDrivenPort;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driver.rest.user.usecase.UserRegisterUseCase;
import com.dcastillo.hexagonalarchitecture.layers.domain.command.user.RegisterUserCommand;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import com.dcastillo.hexagonalarchitecture.layers.domain.utilities.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
@RequiredArgsConstructor
public class UserService implements UserRegisterUseCase {
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
