package com.kamyar.kamyarfndemonstration.dto.request.provider;

import com.kamyar.kamyarfndemonstration.enums.ProductType;
import com.kamyar.kamyarfndemonstration.dto.request.user.UserRegistrationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

import static com.kamyar.kamyarfndemonstration.enums.ValidationMessage.CORPORATION_NAME_MUST_NOT_BE_EMPTY;
import static com.kamyar.kamyarfndemonstration.enums.ValidationMessage.PRODUCT_TYPES_MUST_NOT_BE_EMPTY;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProviderRegistrationDto extends UserRegistrationDto {

    @NotEmpty(message = CORPORATION_NAME_MUST_NOT_BE_EMPTY)
    private String corporationName;

    @NotEmpty(message = PRODUCT_TYPES_MUST_NOT_BE_EMPTY)
    private List<ProductType> productTypes;

}
