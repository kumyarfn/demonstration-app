package com.kamyar.kamyarfndemonstration.dto.request.user;

import lombok.*;

import javax.validation.constraints.NotEmpty;

import static com.kamyar.kamyarfndemonstration.enums.ValidationMessage.*;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserRegistrationDto {

    @NotEmpty(message = FIRST_NAME_MUST_NOT_BE_EMPTY)
    private String firstName;

    @NotEmpty(message = LAST_NAME_MUST_NOT_BE_EMPTY)
    private String lastName;

    @NotEmpty(message = USERNAME_MUST_NOT_BE_EMPTY)
    private String username;

    @NotEmpty(message = PASSWORD_MUST_NOT_BE_EMPTY)
    private String password;

    @NotEmpty(message = PHONE_NUMBER_MUST_NOT_BE_EMPTY)
    private String phoneNumber;

}
