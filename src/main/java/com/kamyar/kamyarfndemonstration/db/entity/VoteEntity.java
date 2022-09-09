package com.kamyar.kamyarfndemonstration.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.kamyar.kamyarfndemonstration.enums.Constants.VOTE_COLLECTION_NAME;

@Document(VOTE_COLLECTION_NAME)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class VoteEntity {

    @Id
    private String id;

    @Field
    @Indexed
    private String userId;

    @Field
    @Indexed
    private String productId;

    @Field
    @Indexed
    private Date creationDate;

    @Field
    private Double vote;

    @Field
    private Boolean isApproved;


}
