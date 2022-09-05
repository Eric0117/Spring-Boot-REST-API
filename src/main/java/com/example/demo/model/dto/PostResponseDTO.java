package com.example.demo.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 4.
 **/
@Data
public class PostResponseDTO {
    private Long id;
    private String title;
    private String detail;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private MemberSummaryResponseDTO member;
}
