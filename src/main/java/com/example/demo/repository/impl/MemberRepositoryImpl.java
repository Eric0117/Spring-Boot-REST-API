package com.example.demo.repository.impl;
import static com.example.demo.model.member.QMember.member;

import com.example.demo.config.jwt.UserPrincipal;
import com.example.demo.model.dto.MemberSummaryResponseDTO;
import com.example.demo.model.member.Member;
import com.example.demo.repository.MemberRepositoryCustom;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
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
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public MemberSummaryResponseDTO getCurrentMember(UserPrincipal currentUser) {
        return queryFactory
                .select(Projections.bean(MemberSummaryResponseDTO.class,
                        member.id,
                        member.username,
                        member.email))
                .from(member)
                .where(member.id.eq(currentUser.getId()))
                .fetchOne();
    }

    @Override
    public MemberSummaryResponseDTO getMemberById(Long id) {
        return queryFactory
                .select(Projections.bean(MemberSummaryResponseDTO.class,
                        member.id,
                        member.username,
                        member.email))
                .from(member)
                .where(member.id.eq(id))
                .fetchOne();
    }

    @Override
    public Page<MemberSummaryResponseDTO> getMembers(Pageable pageable) {

        List<MemberSummaryResponseDTO> results = queryFactory
                .select(Projections.bean(MemberSummaryResponseDTO.class,
                        member.id,
                        member.username,
                        member.email))
                .from(member)
                //.where(member.id.eq(currentUser.getId()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(results, pageable, results.size());
    }
}
