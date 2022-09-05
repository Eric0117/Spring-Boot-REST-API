package com.example.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 5.
 **/
@Data
public class CommentUpdateRequestDTO {
    @NotBlank
    private String detail;
}
