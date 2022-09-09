package com.kamyar.kamyarfndemonstration.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

import static com.kamyar.kamyarfndemonstration.enums.Constants.PRODUCT_COLLECTION_NAME;

@Document(PRODUCT_COLLECTION_NAME)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductEntity {

    @Id
    private String id;

    @Field
    @Indexed
    private String providerId;

    @Field
    private String title;

    @Field
    private Double price;

    @Field
    private Date creationDate;

    @Field
    private String details;

    @Field
    private Boolean isAvailable;

    @Field
    private Boolean isVoteEnabled;

    @Field
    private Boolean isCommentEnabled;

    @Field
    private Boolean buyersOnlyReview;

    @Field
    private Double averageVote;

}
