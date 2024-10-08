package com.dcastillo.hexagonalarchitecture.layers.application.core.user.usecase;

import com.dcastillo.hexagonalarchitecture.layers.domain.command.user.RegisterUserCommand;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;

public interface UserRegisterUseCase {
    User registerUser(RegisterUserCommand command);
}