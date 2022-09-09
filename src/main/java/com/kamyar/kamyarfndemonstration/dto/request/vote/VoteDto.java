package com.kamyar.kamyarfndemonstration.dto.request.vote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.kamyar.kamyarfndemonstration.enums.ValidationMessage.*;

@Getter  @Setter @AllArgsConstructor @NoArgsConstructor
public class VoteDto {

    @NotEmpty(message = USER_ID_MUST_NOT_BE_EMPTY)
    private String userId;

    @NotEmpty(message = PRODUCT_ID_MUST_NOT_BE_EMPTY)
    private String productId;

    @NotNull(message = VOTE_MUST_NOT_BE_NULL)
    private Double vote;

}
