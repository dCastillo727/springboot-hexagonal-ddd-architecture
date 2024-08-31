package com.dcastillo.hexagonalarchitecture.layers.application.dto.user.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String username;
    private String email;
}
