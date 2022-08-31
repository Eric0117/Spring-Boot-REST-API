package com.example.demo.repository.impl;

import com.example.demo.repository.PostRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory queryFactory;
}
