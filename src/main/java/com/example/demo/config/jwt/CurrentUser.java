package com.example.demo.config.jwt;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target 은 Java compiler 가 annotation 이 어디에 적용될지 결정하기 위해 사용합니다.
 *
 * 예를 들어 위에서 사용한 @Service 의 ElementType.TYPE 은 해당 Annotation 은 타입 선언 시 사용한다는 의미입니다.
 *
 * 종류는 다음과 같습니다.
 *
 * ElementType.PACKAGE : 패키지 선언
 * ElementType.TYPE : 타입 선언
 * ElementType.ANNOTATION_TYPE : 어노테이션 타입 선언
 * ElementType.CONSTRUCTOR : 생성자 선언
 * ElementType.FIELD : 멤버 변수 선언
 * ElementType.LOCAL_VARIABLE : 지역 변수 선언
 * ElementType.METHOD : 메서드 선언
 * ElementType.PARAMETER : 전달인자 선언
 * ElementType.TYPE_PARAMETER : 전달인자 타입 선언
 * ElementType.TYPE_USE : 타입 선언
 */
@Target({ ElementType.PARAMETER, ElementType.ANNOTATION_TYPE }) // Meta Annotation으로 custom-annotation 을 생성할 때 주로 사용
/**
 * @Retetion 은 Annotation 이 실제로 적용되고 유지되는 범위를 의미합니다.
 *
 * Policy 에 관련된 Annotation 으로 컴파일 이후에도 JVM 에서 참조가 가능한 RUNTIME 으로 지정합니다.
 *
 * 종류는 다음과 같습니다.
 *
 * RetentionPolicy.RUNTIME
 * RetentionPolicy.CLASS
 * RetentionPolicy.SOURCE
 *
 * RetentionPolicy.RUNTIME 은 컴파일 이후에도 JVM 에 의해서 계속 참조가 가능합니다. 주로 리플렉션이나 로깅에 많이 사용됩니다.
 * RetentionPolicy.CLASS 은 컴파일러가 클래스를 참조할 때가지 유효합니다.
 * RetentionPolicy.SOURCE 은 컴파일 전까지만 유효합니다. 즉, 컴파일 이후에는 사라지게 됩니다.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented // @Documented는 javadoc2으로 api 문서를 만들 때 어노테이션에 대한 설명도 포함하도록 지정
@AuthenticationPrincipal // 로그인한 사용자의 정보를 파라메터로 받고 싶을때 기존에는 다음과 같이 Principal 객체로 받아서 사용
public @interface CurrentUser {
}
