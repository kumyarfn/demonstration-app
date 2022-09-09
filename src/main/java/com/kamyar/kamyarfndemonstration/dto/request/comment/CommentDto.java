package com.kamyar.kamyarfndemonstration.dto.request.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

import static com.kamyar.kamyarfndemonstration.enums.ValidationMessage.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CommentDto {

    @NotEmpty(message = USER_ID_MUST_NOT_BE_EMPTY)
    private String userId;

    @NotEmpty(message = PRODUCT_ID_MUST_NOT_BE_EMPTY)
    private String productId;

    @NotEmpty(message = COMMENT_MUST_NOT_BE_NULL)
    private String comment;
}
