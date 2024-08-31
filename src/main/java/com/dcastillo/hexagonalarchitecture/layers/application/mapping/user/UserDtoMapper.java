package com.dcastillo.hexagonalarchitecture.layers.application.mapping.user;

import com.dcastillo.hexagonalarchitecture.layers.domain.command.user.RegisterUserCommand;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.EmailAddress;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import com.dcastillo.hexagonalarchitecture.layers.application.dto.user.request.UserRegisterFormDto;
import com.dcastillo.hexagonalarchitecture.layers.application.dto.user.response.UserResponseDto;
import org.springframework.stereotype.Component;

public class UserDtoMapper {
    public static UserResponseDto toUserResponseDto(User user) {
        return UserResponseDto.builder()
                .email(user.getEmailAddress().value())
                .username(user.getUsername())
                .build();
    }

    public static RegisterUserCommand toRegisterUserCommand(UserRegisterFormDto userRegisterFormDto) {
        return RegisterUserCommand.issue(
                EmailAddress.from(userRegisterFormDto.getEmail()),
                userRegisterFormDto.getUsername(),
                userRegisterFormDto.getPassword()
        );
    }
}