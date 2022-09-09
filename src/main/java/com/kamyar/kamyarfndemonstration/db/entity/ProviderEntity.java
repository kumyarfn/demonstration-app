package com.kamyar.kamyarfndemonstration.db.entity;

import com.kamyar.kamyarfndemonstration.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.kamyar.kamyarfndemonstration.enums.Constants.PROVIDER_COLLECTION_NAME;

@Document(PROVIDER_COLLECTION_NAME)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProviderEntity {

    @Id
    private String id;

    @Field
    private String userId;

    @Field
    @Indexed
    private String corporationName;

    @Field
    private LocalDate lastAddedProduct;

    @Field
    private List<ProductType> productTypes;


}
