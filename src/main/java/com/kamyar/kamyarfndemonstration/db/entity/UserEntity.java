package com.kamyar.kamyarfndemonstration.db.entity;

import com.kamyar.kamyarfndemonstration.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

import static com.kamyar.kamyarfndemonstration.enums.Constants.USER_COLLECTION_NAME;

@Document(USER_COLLECTION_NAME)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserEntity {

    @Id
    private String id;

    @Field
    @Indexed
    private String username;

    @Field
    private String password;

    @Field
    @Indexed
    private String phoneNumber;

    @Field
    private String firstName;

    @Field
    private String lastName;

    @Field
    private Role role;

    @Field
    private Boolean isActive;

    @Field
    private Boolean isNonLocked;

    @Field
    private LocalDate joinDate;


}
