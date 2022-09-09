package com.kamyar.kamyarfndemonstration.db.repository;

import com.kamyar.kamyarfndemonstration.db.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

}
