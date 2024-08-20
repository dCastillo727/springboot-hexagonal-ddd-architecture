package com.dcastillo.hexagonalarchitecture.layers.application.service.user;

import com.dcastillo.hexagonalarchitecture.common.utils.exceptions.user.UserAlreadyExistsException;
import com.dcastillo.hexagonalarchitecture.common.utils.exceptions.user.UserNotFoundException;
import com.dcastillo.hexagonalarchitecture.common.utils.exceptions.user.WrongUserCredentialsException;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driven.messaging.MessagePublisher;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driven.persistence.repository.user.UserRepositoryDrivenPort;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driver.rest.user.usecase.UserLoginUseCase;
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
public class UserService implements UserRegisterUseCase, UserLoginUseCase {
    private final UserRepositoryDrivenPort repository;
    private final PasswordEncryptor passwordEncryptor;
    private final MessagePublisher messagePublisher;

    @Override
    public User login(String username, String password) {
        Optional<User> user = repository.findByUsername(username);

        if (user.isEmpty())
            throw new UserNotFoundException("User not found");

        boolean checkPassword = passwordEncryptor.matches(password, user.get().getPassword());

        if (!checkPassword) throw new WrongUserCredentialsException("Wrong password or username");

        return user.get();
    }

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

        messagePublisher.publish(user.listEvents());
        user.clearEvents();

        user.setPassword(command.password());
        return user;
    }
}
