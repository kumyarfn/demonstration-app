package com.kamyar.kamyarfndemonstration.db.entity;

import com.kamyar.kamyarfndemonstration.db.entity.document.ProductRecord;
import com.kamyar.kamyarfndemonstration.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.kamyar.kamyarfndemonstration.enums.Constants.SALE_COLLECTION_NAME;

@Document(SALE_COLLECTION_NAME)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SaleEntity {

    @Id
    private String id;

    @Field
    @Indexed
    private String userId;

    @Field
    @Indexed
    private Set<ProductRecord> products;

    @Field
    private Date date;

    @Field
    private ProductStatus status;


}
