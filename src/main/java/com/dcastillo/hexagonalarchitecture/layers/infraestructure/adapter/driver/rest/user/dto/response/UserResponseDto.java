package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driver.rest.user.dto.response;

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
