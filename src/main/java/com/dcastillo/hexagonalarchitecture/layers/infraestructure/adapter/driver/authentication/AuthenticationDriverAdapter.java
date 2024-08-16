package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.authentication;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DriverAdapter;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driver.authentication.AuthenticationDriverPort;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@DriverAdapter
@RequiredArgsConstructor
public class AuthenticationDriverAdapter implements AuthenticationDriverPort {
    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticate(User user) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }
}
