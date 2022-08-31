package com.example.demo.model.role;

import com.example.demo.model.member.Member;
import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
@Embeddable
@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberRoleId implements Serializable {

    private Member member;
    private Role role;

}