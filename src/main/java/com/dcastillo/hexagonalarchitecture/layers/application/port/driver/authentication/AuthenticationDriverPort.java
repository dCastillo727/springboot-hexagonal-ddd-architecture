package com.dcastillo.hexagonalarchitecture.layers.application.port.driver.authentication;

import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;

public interface AuthenticationDriverPort {
    void authenticate(User user);
}
