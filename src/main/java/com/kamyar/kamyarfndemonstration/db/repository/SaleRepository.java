package com.kamyar.kamyarfndemonstration.db.repository;

import com.kamyar.kamyarfndemonstration.db.entity.SaleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaleRepository extends MongoRepository<SaleEntity, String> {

}
