package com.example.demo.model.role;

import com.example.demo.model.member.Member;
import lombok.*;

import javax.persistence.*;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode // MemberRole은 Member에서 Set으로 저장되기 때문에 equals와 hashcode를 재정의
@IdClass(MemberRoleId.class) // 여러 개의 필드를 primary key로 사용하기 위해 @IdClass를 선언. MemberRoleId 클래스에 정의된 필드와 동일한 필드를, MemberRole에서 @Id로 선언해주면, composite key로 만들어낼 수 있음
public class MemberRole {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
}
