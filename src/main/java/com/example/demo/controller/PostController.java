package com.example.demo.controller;

import com.example.demo.advice.exception.exceptions.InvalidSearchTypeException;
import com.example.demo.common.CommonResult;
import com.example.demo.common.PageResult;
import com.example.demo.common.SingleResult;
import com.example.demo.config.jwt.CurrentUser;
import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.dto.*;
import com.example.demo.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "Get Post List", description = "Get Post list")
    @GetMapping
    public PageResult<PostResponseDTO> getPosts(@Valid PageRequestDTO pageRequestDto, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidSearchTypeException();
        }

        return postService.getPosts(pageRequestDto.of());
    }

    @Operation(summary = "Get Post", description = "Get Post by id")
    @GetMapping("/{id}")
    public SingleResult<PostResponseDTO> getPostById(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @Operation(summary = "Add Post", description = "Add member")
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public CommonResult addPost(@Valid @RequestBody PostAddRequestDTO postAddRequestDTO, Errors errors, @CurrentUser UserPrincipal currentUser) {
        if (errors.hasErrors()) {
            throw new InvalidSearchTypeException();
        }

        return postService.addPost(postAddRequestDTO, currentUser);
    }

    @Operation(summary = "Update Post", description = "Update Post (If profile belongs to logged in user or logged in user is admin)")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public CommonResult updatePost(@PathVariable Long id, @Valid @RequestBody PostUpdateRequestDTO postUpdateRequestDTO, @CurrentUser UserPrincipal currentUser) {
        return postService.updatePost(id, postUpdateRequestDTO, currentUser);
    }

    @Operation(summary = "Delete Post", description = "Delete Post (If profile belongs to logged in user or logged in user is admin)")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public CommonResult deletePost(@PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
        return postService.deletePost(id, currentUser);
    }
}
