package com.example.demo.service.impl;

import com.example.demo.advice.exception.exceptions.AccessDeniedException;
import com.example.demo.advice.exception.exceptions.MemberNotExistException;
import com.example.demo.advice.exception.exceptions.PostNotExistException;
import com.example.demo.common.CommonResult;
import com.example.demo.common.PageResult;
import com.example.demo.common.SingleResult;
import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.dto.PostAddRequestDTO;
import com.example.demo.model.dto.PostResponseDTO;
import com.example.demo.model.dto.PostUpdateRequestDTO;
import com.example.demo.model.member.Member;
import com.example.demo.model.post.Post;
import com.example.demo.model.role.RoleName;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import com.example.demo.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);
    private final ResponseService responseService;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    public CommonResult addPost(PostAddRequestDTO postAddRequestDTO, UserPrincipal currentUser) {
        Member member = memberRepository.findById(currentUser.getId())
                .orElseThrow(MemberNotExistException::new);

        Post post = Post.builder()
                .title(postAddRequestDTO.getTitle())
                .detail(postAddRequestDTO.getDetail())
                .isActive(true)
                .member(member)
                .build();

        postRepository.save(post);

        return responseService.getSuccessResult();
    }

    @Override
    public SingleResult<PostResponseDTO> getPost(Long id) {
        PostResponseDTO post = postRepository.getPost(id);
        return responseService.getSingleResult(post);
    }

    @Override
    public PageResult<PostResponseDTO> getPosts(Pageable pageable) {
        Page<PostResponseDTO> postList = postRepository.getPosts(pageable);
        return responseService.getPageResult(postList);
    }

    @Override
    public CommonResult updatePost(Long id, PostUpdateRequestDTO postUpdateRequestDTO, UserPrincipal currentUser) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotExistException::new);
        if (!post.getMember().getId().equals(currentUser.getId()) && !currentUser.getAuthorities()
                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            throw new AccessDeniedException();
        }

        post.updatePost(postUpdateRequestDTO.getTitle(), postUpdateRequestDTO.getDetail());
        postRepository.save(post);
        return responseService.getSuccessResult();
    }

    @Override
    public CommonResult deletePost(Long id, UserPrincipal currentUser) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotExistException::new);
        if (!post.getMember().getId().equals(currentUser.getId()) && !currentUser.getAuthorities()
                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            throw new AccessDeniedException();
        }

        if(post.getIsActive()) {
            post.deActive();
        }

        postRepository.save(post);
        return responseService.getSuccessResult();
    }

}
