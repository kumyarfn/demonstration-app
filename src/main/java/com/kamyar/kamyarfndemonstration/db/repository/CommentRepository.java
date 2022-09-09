package com.kamyar.kamyarfndemonstration.db.repository;

import com.kamyar.kamyarfndemonstration.db.entity.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<CommentEntity, String> {


}
