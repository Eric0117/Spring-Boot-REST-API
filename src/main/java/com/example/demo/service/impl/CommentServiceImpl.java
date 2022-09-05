package com.example.demo.service.impl;

import com.example.demo.advice.exception.exceptions.AccessDeniedException;
import com.example.demo.advice.exception.exceptions.CommentNotExistException;
import com.example.demo.advice.exception.exceptions.MemberNotExistException;
import com.example.demo.advice.exception.exceptions.PostNotExistException;
import com.example.demo.common.CommonResult;
import com.example.demo.common.PageResult;
import com.example.demo.common.SingleResult;
import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.comment.Comment;
import com.example.demo.model.dto.*;
import com.example.demo.model.member.Member;
import com.example.demo.model.post.Post;
import com.example.demo.model.role.RoleName;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.CommentService;
import com.example.demo.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 5.
 **/
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ResponseService responseService;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Override
    public PageResult<CommentResponseDTO> getComments(Long postId, Pageable pageable) {
        Page<CommentResponseDTO> commentList = commentRepository.getComments(postId, pageable);
        return responseService.getPageResult(commentList);
    }

    @Override
    public SingleResult<CommentResponseDTO> getComment(Long postId, Long id) {
        CommentResponseDTO comment = commentRepository.getComment(postId, id);
        return responseService.getSingleResult(comment);
    }

    @Override
    public CommonResult addComment(Long postId, CommentAddRequestDTO commentAddRequestDTO, UserPrincipal currentUser) {
        Member member = memberRepository.findById(currentUser.getId())
                .orElseThrow(MemberNotExistException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotExistException::new);

        Comment comment = Comment.builder()
                .detail(commentAddRequestDTO.getDetail())
                .member(member)
                .post(post)
                .isActive(true)
                .build();

        commentRepository.save(comment);

        return responseService.getSuccessResult();
    }

    @Override
    public CommonResult updateComment(Long postId, Long id, CommentUpdateRequestDTO commentUpdateRequestDTO, UserPrincipal currentUser) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotExistException::new);

        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotExistException::new);
        if (!comment.getMember().getId().equals(currentUser.getId()) && !currentUser.getAuthorities()
                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            throw new AccessDeniedException();
        }

        comment.updateComment(commentUpdateRequestDTO.getDetail());
        commentRepository.save(comment);
        return responseService.getSuccessResult();
    }

    @Override
    public CommonResult deleteComment(Long postId, Long id, UserPrincipal currentUser) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotExistException::new);
        if (!comment.getMember().getId().equals(currentUser.getId()) && !currentUser.getAuthorities()
                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            throw new AccessDeniedException();
        }

        if(comment.getIsActive()) {
            comment.deActive();
        }

        commentRepository.save(comment);
        return responseService.getSuccessResult();
    }
}
