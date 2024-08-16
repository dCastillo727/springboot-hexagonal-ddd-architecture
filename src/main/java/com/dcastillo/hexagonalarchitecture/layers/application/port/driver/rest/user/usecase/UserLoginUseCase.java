package com.dcastillo.hexagonalarchitecture.layers.application.port.driver.rest.user.usecase;

import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;

public interface UserLoginUseCase {
    User login(String username, String password);
}
