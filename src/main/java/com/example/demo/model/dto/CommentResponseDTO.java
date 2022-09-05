package com.example.demo.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 5.
 **/
@Data
public class CommentResponseDTO {
    private Long id;
    private String detail;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private MemberSummaryResponseDTO member;
    private PostResponseDTO post;
}
