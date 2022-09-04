package com.example.demo.model.post;

import com.example.demo.config.BooleanToYNConverter;
import com.example.demo.model.common.BaseEntity;
import com.example.demo.model.member.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 31.
 **/
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "post")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "detail")
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean isActive;

    public Boolean getIsActive() {
        return isActive;
    }

    public void deActive() {
        this.isActive = false;
    }

    public void updatePost(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }
}
