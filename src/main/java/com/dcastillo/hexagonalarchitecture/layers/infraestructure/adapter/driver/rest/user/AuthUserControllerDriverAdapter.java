package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.rest.user;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DriverAdapter;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driver.rest.user.AuthUserControllerDriverPort;
import com.dcastillo.hexagonalarchitecture.layers.application.core.user.usecase.UserRegisterUseCase;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.authentication.AuthenticationDriverAdapter;
import com.dcastillo.hexagonalarchitecture.layers.application.dto.user.request.UserLoginFormDto;
import com.dcastillo.hexagonalarchitecture.layers.application.dto.user.request.UserRegisterFormDto;
import com.dcastillo.hexagonalarchitecture.layers.application.dto.user.response.UserResponseDto;
import com.dcastillo.hexagonalarchitecture.layers.application.mapping.user.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@DriverAdapter
@RequiredArgsConstructor
public class AuthUserControllerDriverAdapter implements AuthUserControllerDriverPort {
    private final UserRegisterUseCase userRegisterUseCase;
    private final AuthenticationDriverAdapter authenticationDriverAdapter;

    @Override
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserLoginFormDto loginForm) {
        if (!loginForm.isValid()) throw new BadCredentialsException("Invalid username or password");

        User user = authenticationDriverAdapter.login(loginForm.getUsername(), loginForm.getPassword());

        return new ResponseEntity<>(UserDtoMapper.toUserResponseDto(user), HttpStatus.OK);
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRegisterFormDto registerForm) {
        if (!registerForm.isValid()) throw new BadCredentialsException("Invalid register request");

        User user = userRegisterUseCase.registerUser(UserDtoMapper.toRegisterUserCommand(registerForm));
        user = authenticationDriverAdapter.login(user.getUsername(), registerForm.getPassword());

        return new ResponseEntity<>(UserDtoMapper.toUserResponseDto(user), HttpStatus.CREATED);
    }
}
