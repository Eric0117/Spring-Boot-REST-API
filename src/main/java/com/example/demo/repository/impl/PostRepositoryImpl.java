package com.example.demo.repository.impl;
import static com.example.demo.model.post.QPost.post;
import static com.example.demo.model.member.QMember.member;

import com.example.demo.model.dto.MemberSummaryResponseDTO;
import com.example.demo.model.dto.PostResponseDTO;
import com.example.demo.repository.PostRepositoryCustom;
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
 * @Since 22. 8. 31.
 **/
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostResponseDTO> getPosts(Pageable pageable) {
        List<PostResponseDTO> results = queryFactory
                .select(Projections.bean(PostResponseDTO.class,
                        post.id,
                        post.title,
                        post.detail,
                        post.isActive,
                        Projections.bean(MemberSummaryResponseDTO.class,
                                member.id,
                                member.username,
                                member.email).as("member"),
                        post.createdDate,
                        post.modifiedDate))
                .from(post)
                .innerJoin(member).on(post.member.id.eq(member.id))
                .where(post.isActive.eq(true))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(results, pageable, results.size());
    }

    @Override
    public PostResponseDTO getPost(Long id) {
        return queryFactory
                .select(Projections.bean(PostResponseDTO.class,
                        post.id,
                        post.title,
                        post.detail,
                        post.isActive,
                        Projections.bean(MemberSummaryResponseDTO.class,
                                member.id,
                                member.username,
                                member.email).as("member"),
                        post.createdDate,
                        post.modifiedDate))
                .from(post)
                .innerJoin(member).on(post.member.id.eq(member.id))
                .where(post.id.eq(id))
                .fetchOne();
    }
}
