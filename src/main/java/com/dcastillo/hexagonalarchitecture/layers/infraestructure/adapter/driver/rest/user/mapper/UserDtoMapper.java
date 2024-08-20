package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.rest.user.mapper;

import com.dcastillo.hexagonalarchitecture.layers.domain.command.user.RegisterUserCommand;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.EmailAddress;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.rest.user.dto.request.UserRegisterFormDto;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.rest.user.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public UserResponseDto toUserResponseDto(User user) {
        return UserResponseDto.builder()
                .email(user.getEmailAddress().value())
                .username(user.getUsername())
                .build();
    }

    public RegisterUserCommand toRegisterUserCommand(UserRegisterFormDto userRegisterFormDto) {
        return RegisterUserCommand.issue(
                EmailAddress.from(userRegisterFormDto.getEmail()),
                userRegisterFormDto.getUsername(),
                userRegisterFormDto.getPassword()
        );
    }
}