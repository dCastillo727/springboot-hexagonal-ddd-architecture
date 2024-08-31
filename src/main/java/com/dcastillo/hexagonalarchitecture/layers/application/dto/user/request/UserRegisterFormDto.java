package com.dcastillo.hexagonalarchitecture.layers.application.dto.user.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterFormDto {
    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @JsonIgnore
    public boolean isValid() {
        return StringUtils.isNotBlank(username) && StringUtils.isNotBlank(email) && StringUtils.isNotBlank(password);
    }
}
