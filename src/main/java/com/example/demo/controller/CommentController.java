package com.example.demo.controller;

import com.example.demo.advice.exception.exceptions.InvalidSearchTypeException;
import com.example.demo.common.CommonResult;
import com.example.demo.common.PageResult;
import com.example.demo.common.SingleResult;
import com.example.demo.config.jwt.CurrentUser;
import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.dto.CommentAddRequestDTO;
import com.example.demo.model.dto.CommentResponseDTO;
import com.example.demo.model.dto.CommentUpdateRequestDTO;
import com.example.demo.model.dto.PageRequestDTO;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 5.
 **/
@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public PageResult<CommentResponseDTO> getComments(@PathVariable(name = "postId") Long postId, @Valid PageRequestDTO pageRequestDto, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidSearchTypeException();
        }

        return commentService.getComments(postId, pageRequestDto.of());
    }

    @GetMapping("/{id}")
    public SingleResult<CommentResponseDTO> getComment(@PathVariable(name = "postId") Long postId, @PathVariable(name = "id") Long id) {
        return commentService.getComment(postId, id);
    }

    @PostMapping
    public CommonResult addComment(@PathVariable(name = "postId") Long postId, @Valid @RequestBody CommentAddRequestDTO commentAddRequestDTO, Errors errors, @CurrentUser UserPrincipal currentUser) {
        if (errors.hasErrors()) {
            throw new InvalidSearchTypeException();
        }

        return commentService.addComment(postId, commentAddRequestDTO, currentUser);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public CommonResult updateComment(@PathVariable(name = "postId") Long postId,
         @PathVariable(name = "id") Long id, @Valid @RequestBody CommentUpdateRequestDTO commentUpdateRequestDTO,
         @CurrentUser UserPrincipal currentUser) {
        return commentService.updateComment(postId, id, commentUpdateRequestDTO, currentUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public CommonResult deleteComment(@PathVariable(name = "postId") Long postId, @PathVariable(name = "id") Long id, @CurrentUser UserPrincipal currentUser) {
        return commentService.deleteComment(postId, id, currentUser);
    }
}
