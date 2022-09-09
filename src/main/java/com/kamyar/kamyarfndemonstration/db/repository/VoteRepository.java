package com.kamyar.kamyarfndemonstration.db.repository;

import com.kamyar.kamyarfndemonstration.db.entity.VoteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepository extends MongoRepository<VoteEntity, String> {


}
