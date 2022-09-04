package com.example.demo.service;

import com.example.demo.common.CommonResult;
import com.example.demo.common.PageResult;
import com.example.demo.common.SingleResult;
import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.dto.PostAddRequestDTO;
import com.example.demo.model.dto.PostResponseDTO;
import com.example.demo.model.dto.PostUpdateRequestDTO;
import org.springframework.data.domain.Pageable;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
public interface PostService {
    CommonResult addPost(PostAddRequestDTO postAddRequestDTO, UserPrincipal currentUser);
    PageResult<PostResponseDTO> getPosts(Pageable pageable);
    SingleResult<PostResponseDTO> getPost(Long id);
    CommonResult updatePost(Long id, PostUpdateRequestDTO postUpdateRequestDTO, UserPrincipal currentUser);
    CommonResult deletePost(Long id, UserPrincipal currentUser);
}
