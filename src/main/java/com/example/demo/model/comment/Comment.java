package com.example.demo.model.comment;

import com.example.demo.config.BooleanToYNConverter;
import com.example.demo.model.common.BaseEntity;
import com.example.demo.model.member.Member;
import com.example.demo.model.post.Post;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "detail")
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean isActive;

    public Boolean getIsActive() {
        return isActive;
    }

    public void deActive() {
        this.isActive = false;
    }

    public void updateComment(String detail) {
        this.detail = detail;
    }
}
