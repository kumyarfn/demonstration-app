package com.kamyar.kamyarfndemonstration.controller;

import com.kamyar.kamyarfndemonstration.dto.request.comment.CommentDto;
import com.kamyar.kamyarfndemonstration.dto.request.vote.VoteDto;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import com.kamyar.kamyarfndemonstration.service.CommentService;
import com.kamyar.kamyarfndemonstration.service.VoteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kamyar.kamyarfndemonstration.enums.Constants.BEARER_AUTH;

@RestController
@RequestMapping("/review")
@SecurityRequirement(name = BEARER_AUTH)
public class ReviewController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/vote")
    @PreAuthorize("hasAuthority('user::read')")
    public ResponseEntity<HttpResponse> saveVote(@RequestBody @Valid VoteDto dto) {
        return ResponseEntity.ok(voteService.saveVote(dto));
    }

    @PutMapping("/vote/approve")
    @PreAuthorize("hasAuthority('pm::write')")
    public ResponseEntity<HttpResponse> approveVote(@RequestParam String voteId) {
        return ResponseEntity.ok(voteService.approveVote(voteId));
    }

    @GetMapping("/vote/count")
    @PreAuthorize("hasAuthority('user::read')")
    public ResponseEntity<HttpResponse> getProductsVotesCount(@RequestParam String productId) {
        return ResponseEntity.ok(voteService.getProductsVotesCount(productId));
    }

    @GetMapping("/vote/user-enable")
    @PreAuthorize("hasAuthority('user::read')")
    public ResponseEntity<HttpResponse> getUserAbilityToVote(@RequestParam String userId, @RequestParam String productId) {
        return ResponseEntity.ok(voteService.canUserVote(userId, productId));
    }

    @PostMapping("/comment")
    @PreAuthorize("hasAuthority('user::read')")
    public ResponseEntity<HttpResponse> saveComment(@RequestBody @Valid CommentDto dto) {
        return ResponseEntity.ok(commentService.saveComment(dto));
    }

    @PutMapping("/comment/approve")
    @PreAuthorize("hasAuthority('pm::write')")
    public ResponseEntity<HttpResponse> approveComment(@RequestParam String commentId) {
        return ResponseEntity.ok(commentService.approveComment(commentId));
    }

    @GetMapping("/comment/last-three")
    @PreAuthorize("hasAuthority('user::read')")
    public ResponseEntity<HttpResponse> getLastThreeApprovedComments(@RequestParam String productId) {
        return ResponseEntity.ok(commentService.getLastThreeComments(productId));
    }

    @GetMapping("/comment/count")
    @PreAuthorize("hasAuthority('user::read')")
    public ResponseEntity<HttpResponse> getProductsCommentsCount(@RequestParam String productId) {
        return ResponseEntity.ok(commentService.getCommentsCount(productId));
    }

    @GetMapping("/comment/user-enable")
    @PreAuthorize("hasAuthority('user::read')")
    public ResponseEntity<HttpResponse> getUserAbilityToComment(@RequestParam String userId, @RequestParam String productId) {
        return ResponseEntity.ok(commentService.canUserComment(userId, productId));
    }

}
