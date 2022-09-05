package com.example.demo.service;

import com.example.demo.common.CommonResult;
import com.example.demo.common.PageResult;
import com.example.demo.common.SingleResult;
import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.dto.*;
import org.springframework.data.domain.Pageable;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 5.
 **/
public interface CommentService {
    PageResult<CommentResponseDTO> getComments(Long postId, Pageable pageable);
    SingleResult<CommentResponseDTO> getComment(Long postId, Long id);
    CommonResult addComment(Long postId, CommentAddRequestDTO commentAddRequestDTO, UserPrincipal currentUser);
    CommonResult updateComment(Long postId, Long id, CommentUpdateRequestDTO commentUpdateRequestDTO, UserPrincipal currentUser);
    CommonResult deleteComment(Long postId, Long id, UserPrincipal currentUser);
}
