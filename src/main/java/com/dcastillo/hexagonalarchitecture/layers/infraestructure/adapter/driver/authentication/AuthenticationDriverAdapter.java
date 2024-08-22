package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.authentication;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DriverAdapter;
import com.dcastillo.hexagonalarchitecture.layers.application.event.authentication.UserLoggedInEvent;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driver.authentication.AuthenticationDriverPort;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.providers.authentication.AuthUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@DriverAdapter
@RequiredArgsConstructor
public class AuthenticationDriverAdapter implements AuthenticationDriverPort {
    private final AuthenticationManager authenticationManager;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public User login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        AuthUserDetails authUserDetails = (AuthUserDetails) authentication.getPrincipal();

        User user = authUserDetails.getUser();
        eventPublisher.publishEvent(UserLoggedInEvent.issue(user));
        return user;
    }

    @Override
    public void logout() {

    }

    @Override
    public User getSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();

        return userDetails.getUser();
    }
}
