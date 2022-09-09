package com.kamyar.kamyarfndemonstration.db.entity.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductRecord {

    private String productId;

    private Long currentPrice;

}
