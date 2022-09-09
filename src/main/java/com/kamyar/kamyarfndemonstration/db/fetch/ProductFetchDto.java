package com.kamyar.kamyarfndemonstration.db.fetch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductFetchDto {

    private String id;

    private String providerId;

    private String title;

    private String details;

    private Boolean isAvailable;

    private Boolean isVoteEnabled;

    private Boolean isCommentEnabled;

    private Double averageVote;
}
