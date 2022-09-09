package com.kamyar.kamyarfndemonstration.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.kamyar.kamyarfndemonstration.enums.ValidationMessage.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductAddingDto {

    @NotEmpty(message = Provider_ID_MUST_NOT_BE_EMPTY)
    private String providerId;

    @NotEmpty(message = TITLE_MUST_NOT_BE_EMPTY)
    private String title;

    @NotNull(message = PRICE_MUST_NOT_BE_NULL)
    private Double price;

    private String details;

    @NotNull(message = IS_AVAILABLE_MUST_NOT_BE_NULL)
    private Boolean isAvailable;

    @NotNull(message = IS_VOTE_ENABLED_MUST_NOT_BE_NULL)
    private Boolean isVoteEnabled;

    @NotNull(message = IS_COMMENT_ENABLED_MUST_NOT_BE_NULL)
    private Boolean isCommentEnabled;

    @NotNull(message = BUYERS_ONLY_REVIEW_MUST_NOT_BE_NULL)
    private Boolean buyersOnlyReview;

}
