package com.example.demo.repository.impl;

import static com.example.demo.model.comment.QComment.comment;
import static com.example.demo.model.member.QMember.member;
import static com.example.demo.model.post.QPost.post;
import com.example.demo.model.dto.CommentResponseDTO;
import com.example.demo.model.dto.MemberSummaryResponseDTO;
import com.example.demo.model.dto.PostResponseDTO;
import com.example.demo.repository.CommentRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 5.
 **/
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CommentResponseDTO> getComments(Long postId, Pageable pageable) {
        List<CommentResponseDTO> results = queryFactory
                .select(Projections.bean(CommentResponseDTO.class,
                        comment.id,
                        comment.detail,
                        comment.createdDate,
                        comment.modifiedDate,
                        Projections.bean(MemberSummaryResponseDTO.class,
                                member.id,
                                member.username,
                                member.email).as("member"),
                        Projections.bean(PostResponseDTO.class,
                                post.id,
                                post.title,
                                post.detail,
                                post.createdDate,
                                post.modifiedDate).as("post")))
                .from(comment)
                .innerJoin(member).on(comment.member.id.eq(member.id))
                .innerJoin(post).on(comment.post.id.eq(post.id))
                .where(comment.post.id.eq(postId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(results, pageable, results.size());
    }

    @Override
    public CommentResponseDTO getComment(Long postId, Long id) {
        return queryFactory
                .select(Projections.bean(CommentResponseDTO.class,
                        comment.id,
                        comment.detail,
                        comment.createdDate,
                        comment.modifiedDate,
                        Projections.bean(MemberSummaryResponseDTO.class,
                                member.id,
                                member.username,
                                member.email).as("member"),
                        Projections.bean(PostResponseDTO.class,
                                post.id,
                                post.title,
                                post.detail,
                                post.createdDate,
                                post.modifiedDate).as("post")))
                .from(comment)
                .innerJoin(member).on(comment.member.id.eq(member.id))
                .innerJoin(post).on(comment.post.id.eq(post.id))
                .fetchJoin()
                .where(comment.post.id.eq(postId).and(comment.id.eq(id)))
                .fetchOne();

    }
}
