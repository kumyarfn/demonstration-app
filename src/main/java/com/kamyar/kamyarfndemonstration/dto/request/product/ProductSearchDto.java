package com.kamyar.kamyarfndemonstration.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductSearchDto{

    private String providerId;

    private Double price;

}
