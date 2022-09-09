package com.kamyar.kamyarfndemonstration.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

import static com.kamyar.kamyarfndemonstration.enums.ValidationMessage.PASSWORD_MUST_NOT_BE_EMPTY;
import static com.kamyar.kamyarfndemonstration.enums.ValidationMessage.USERNAME_MUST_NOT_BE_EMPTY;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class LoginDto {

    @NotEmpty(message = USERNAME_MUST_NOT_BE_EMPTY)
    private String username;

    @NotEmpty(message = PASSWORD_MUST_NOT_BE_EMPTY)
    private String password;
}
