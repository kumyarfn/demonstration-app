package com.kamyar.kamyarfndemonstration.service;

import com.kamyar.kamyarfndemonstration.db.entity.ProductEntity;
import com.kamyar.kamyarfndemonstration.db.entity.VoteEntity;
import com.kamyar.kamyarfndemonstration.db.repository.VoteRepository;
import com.kamyar.kamyarfndemonstration.dto.request.vote.VoteDto;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import com.kamyar.kamyarfndemonstration.exception.VoteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.kamyar.kamyarfndemonstration.enums.Constants.IS_APPROVED_FIELD;
import static com.kamyar.kamyarfndemonstration.enums.Constants.PRODUCT_ID_FIELD;
import static com.kamyar.kamyarfndemonstration.enums.ResultMessage.*;

@Service
public class VoteService {


    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ProductService productService;

    @Autowired
    private SaleService saleService;

    public HttpResponse saveVote(VoteDto dto) {
        ProductEntity product = productService.getProductById(dto.getProductId());
        if (product.getIsVoteEnabled()) {
            if (product.getBuyersOnlyReview()) {
                if (saleService.userHasNotBoughtProduct(dto.getUserId(), dto.getProductId())) return HttpResponse.create(USER_HAS_NOT_BOUGHT_PRODUCT);
                else {
                    voteRepository.save(getVoteEntityFromDto(dto));
                    return HttpResponse.create(SUCCESS_RESULT.getCode(), VOTE_SUCCESSFULLY_SAVED.getMessage());
                }
            } else {
                voteRepository.save(getVoteEntityFromDto(dto));
                return HttpResponse.create(SUCCESS_RESULT.getCode(), VOTE_SUCCESSFULLY_SAVED.getMessage());
            }
        } else return HttpResponse.create(PRODUCT_VOTING_IS_NOT_ENABLEd);
    }

    public HttpResponse canUserVote(String userId, String productId) {
        ProductEntity product = productService.getProductById(productId);
        if (product.getIsVoteEnabled()) {
            if (product.getBuyersOnlyReview()) {
                if (saleService.userHasNotBoughtProduct(userId, productId))
                    return HttpResponse.create(SUCCESS_RESULT.getCode(), Boolean.FALSE);
                else return HttpResponse.create(SUCCESS_RESULT.getCode(), Boolean.TRUE);
            } else {
                return HttpResponse.create(SUCCESS_RESULT.getCode(), Boolean.TRUE);
            }
        } else return HttpResponse.create(SUCCESS_RESULT.getCode(), Boolean.FALSE);
    }

    public HttpResponse approveVote(String voteId) {
        VoteEntity voteEntity = getVoteById(voteId);
        ProductEntity product = productService.getProductById(voteEntity.getProductId());
        voteEntity.setIsApproved(Boolean.TRUE);
        voteRepository.save(voteEntity);
        long approvedCount = getApprovedVotesCount(product.getId());
        if (approvedCount == 0) product.setAverageVote(voteEntity.getVote());
        else product.setAverageVote(((approvedCount * product.getAverageVote()) + voteEntity.getVote()) / (approvedCount + 1));
        productService.saveProduct(product);
        return HttpResponse.create(SUCCESS_RESULT.getCode(), VOTE_SUCCESSFULLY_APPROVED.getMessage());
    }

    private VoteEntity getVoteById(String voteId) {
        return voteRepository.findById(voteId).orElseThrow(() -> new VoteException(VOTE_ID_IS_WRONG));
    }

    private VoteEntity getVoteEntityFromDto(VoteDto dto) {
        return new VoteEntity(null, dto.getUserId(), dto.getProductId(), new Date(), dto.getVote(), Boolean.FALSE);
    }

    private long getApprovedVotesCount(String productId) {
        return mongoTemplate.count(new Query().addCriteria(Criteria.where(PRODUCT_ID_FIELD).is(productId)
                .and(IS_APPROVED_FIELD).is(Boolean.TRUE)), VoteEntity.class);
    }

    public HttpResponse getProductsVotesCount(String productId) {
        return HttpResponse.create(SUCCESS_RESULT.getCode(), getApprovedVotesCount(productId));
    }

}
