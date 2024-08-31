package com.dcastillo.hexagonalarchitecture.layers.application.port.driver.rest.user;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.port.DriverPort;
import com.dcastillo.hexagonalarchitecture.layers.application.dto.user.request.UserLoginFormDto;
import com.dcastillo.hexagonalarchitecture.layers.application.dto.user.request.UserRegisterFormDto;
import com.dcastillo.hexagonalarchitecture.layers.application.dto.user.response.UserResponseDto;
import org.springframework.http.ResponseEntity;

@DriverPort
public interface AuthUserControllerDriverPort {
    ResponseEntity<UserResponseDto> login(UserLoginFormDto requestBody);

    ResponseEntity<UserResponseDto> register(UserRegisterFormDto requestBody);
}
