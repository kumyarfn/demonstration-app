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

import static com.kamyar.kamyarfndemonstration.enums.Constants.COMMENT_COLLECTION_NAME;

@Document(COMMENT_COLLECTION_NAME)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CommentEntity {

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
    private String comment;

    @Field
    private Boolean isApproved;

}
