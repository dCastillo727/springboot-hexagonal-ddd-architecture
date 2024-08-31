package com.dcastillo.hexagonalarchitecture.layers.application.dto.user.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginFormDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @JsonIgnore
    public boolean isValid() {
        return StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password);
    }
}
