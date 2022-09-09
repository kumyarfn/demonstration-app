package com.kamyar.kamyarfndemonstration.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

import static com.kamyar.kamyarfndemonstration.enums.ValidationMessage.PRODUCT_ID_MUST_NOT_BE_EMPTY;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductUpdateDto {

    @NotEmpty(message = PRODUCT_ID_MUST_NOT_BE_EMPTY)
    private String productId;

    private Boolean isAvailable;

    private Boolean isVoteEnabled;

    private Boolean isCommentEnabled;

    private Boolean buyersOnlyReview;

}
