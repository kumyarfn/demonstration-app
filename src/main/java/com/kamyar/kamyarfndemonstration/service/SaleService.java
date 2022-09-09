package com.kamyar.kamyarfndemonstration.service;

import com.kamyar.kamyarfndemonstration.db.fetch.SaleCheckingFetch;
import com.kamyar.kamyarfndemonstration.enums.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static com.kamyar.kamyarfndemonstration.enums.Constants.*;

@Service
public class SaleService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Boolean userHasNotBoughtProduct(String userId, String productId){
        return mongoTemplate.find(new Query().addCriteria
                (Criteria.where(USER_ID_Field).is(userId)
                        .and(STATUS_Field).is(ProductStatus.ALL_DONE)
                        .and(PRODUCTID_IN_PRODUCT_Field).is(productId)), SaleCheckingFetch.class, SALE_COLLECTION_NAME).isEmpty();
    }
}
