package com.example.demo.repository.impl;
import static com.example.demo.model.member.QMember.member;

import com.example.demo.model.member.Member;
import com.example.demo.repository.MemberRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

}
