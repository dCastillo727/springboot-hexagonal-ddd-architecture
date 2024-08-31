package com.dcastillo.hexagonalarchitecture.layers.application.port.driver.authentication;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.port.DriverPort;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;

@DriverPort
public interface AuthenticationDriverPort {
    User login(String username, String password);
    void logout();
    User getSessionUser();
}
