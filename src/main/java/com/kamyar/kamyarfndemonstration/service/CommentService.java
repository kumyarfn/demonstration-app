package com.kamyar.kamyarfndemonstration.service;

import com.kamyar.kamyarfndemonstration.db.entity.CommentEntity;
import com.kamyar.kamyarfndemonstration.db.entity.ProductEntity;
import com.kamyar.kamyarfndemonstration.db.fetch.CommentFetchingDto;
import com.kamyar.kamyarfndemonstration.db.repository.CommentRepository;
import com.kamyar.kamyarfndemonstration.dto.request.comment.CommentDto;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import com.kamyar.kamyarfndemonstration.exception.CommentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.kamyar.kamyarfndemonstration.enums.Constants.*;
import static com.kamyar.kamyarfndemonstration.enums.ResultMessage.*;

@Service
public class CommentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SaleService saleService;

    public HttpResponse saveComment(CommentDto dto) {
        ProductEntity product = productService.getProductById(dto.getProductId());
        if (product.getIsCommentEnabled()) {
            if (product.getBuyersOnlyReview()) {
                if (saleService.userHasNotBoughtProduct(dto.getUserId(), dto.getProductId())) return HttpResponse.create(USER_HAS_NOT_BOUGHT_PRODUCT);
                else {
                    commentRepository.save(getCommentFromDto(dto));
                    return HttpResponse.create(SUCCESS_RESULT.getCode(), COMMENT_SUCCESSFULLY_SAVED.getMessage());
                }
            } else {
                commentRepository.save(getCommentFromDto(dto));
                return HttpResponse.create(SUCCESS_RESULT.getCode(), COMMENT_SUCCESSFULLY_SAVED.getMessage());
            }
        } else return HttpResponse.create(PRODUCT_COMMENT_IS_NOT__ENABLED);
    }

    public HttpResponse canUserComment(String userId, String productId) {
        ProductEntity product = productService.getProductById(productId);
        if (product.getIsCommentEnabled()) {
            if (product.getBuyersOnlyReview()) {
                if (saleService.userHasNotBoughtProduct(userId, productId))
                    return HttpResponse.create(SUCCESS_RESULT.getCode(), Boolean.FALSE);
                else return HttpResponse.create(SUCCESS_RESULT.getCode(), Boolean.TRUE);
            } else {
                return HttpResponse.create(SUCCESS_RESULT.getCode(), Boolean.TRUE);
            }
        } else return HttpResponse.create(SUCCESS_RESULT.getCode(), Boolean.FALSE);
    }

    public HttpResponse approveComment(String commentId) {
        CommentEntity comment = getCommentById(commentId);
        comment.setIsApproved(Boolean.TRUE);
        commentRepository.save(comment);
        return HttpResponse.create(SUCCESS_RESULT.getCode(), COMMENT_SUCCESSFULLY_APPROVED.getMessage());
    }

    public HttpResponse getLastThreeComments(String productId) {
        return HttpResponse.create(SUCCESS_RESULT.getCode(), getLastThreeCommentsFetchDtos(productId));
    }

    public HttpResponse getCommentsCount(String productId) {
        return HttpResponse.create(SUCCESS_RESULT.getCode(), mongoTemplate.count(new Query()
                        .addCriteria(Criteria.where(PRODUCT_ID_FIELD).is(productId).and(IS_APPROVED_FIELD).is(Boolean.TRUE)),
                CommentEntity.class));
    }

    public List<CommentFetchingDto> getLastThreeCommentsFetchDtos(String productId) {
        return mongoTemplate.find(new Query()
                        .addCriteria(Criteria.where(PRODUCT_ID_FIELD).is(productId).and(IS_APPROVED_FIELD).is(Boolean.TRUE))
                        .with(PageRequest.of(0, 3, Sort.Direction.DESC, CREATION_DATE_FIELD))
                , CommentFetchingDto.class, COMMENT_COLLECTION_NAME);
    }

    private CommentEntity getCommentFromDto(CommentDto dto) {
        return new CommentEntity(null, dto.getUserId(), dto.getProductId(), new Date(), dto.getComment(), Boolean.FALSE);
    }

    private CommentEntity getCommentById(String commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentException(COMMENT_ID_IS_WRONG));
    }

}
