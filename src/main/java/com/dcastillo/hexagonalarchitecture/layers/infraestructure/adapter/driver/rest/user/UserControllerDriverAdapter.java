package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.rest.user;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DriverAdapter;
import com.dcastillo.hexagonalarchitecture.layers.application.service.user.UserService;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.authentication.AuthenticationDriverAdapter;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.rest.user.dto.request.UserLoginFormDto;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.rest.user.dto.request.UserRegisterFormDto;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.rest.user.dto.response.UserResponseDto;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.rest.user.mapper.UserDtoMapper;
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
public class UserControllerDriverAdapter {
    private final UserDtoMapper userDtoMapper;
    private final UserService userService;
    private final AuthenticationDriverAdapter authenticationDriverAdapter;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserLoginFormDto loginForm) {
        if (!loginForm.isValid()) throw new BadCredentialsException("Invalid username or password");

        User user = userService.login(loginForm.getUsername(), loginForm.getPassword());
        user.setPassword(loginForm.getPassword());
        authenticationDriverAdapter.authenticate(user);

        return new ResponseEntity<>(userDtoMapper.toUserResponseDto(user), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRegisterFormDto registerForm) {
        if (!registerForm.isValid()) throw new BadCredentialsException("Invalid register request");

        User user = userService.registerUser(userDtoMapper.toRegisterUserCommand(registerForm));
        authenticationDriverAdapter.authenticate(user);

        return new ResponseEntity<>(userDtoMapper.toUserResponseDto(user), HttpStatus.CREATED);
    }
}
