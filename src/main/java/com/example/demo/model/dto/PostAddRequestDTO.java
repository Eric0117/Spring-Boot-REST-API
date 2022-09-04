package com.example.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author Eric
 * @Description
 * @Since 22. 9. 4.
 **/
@Data
public class PostAddRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String detail;
}
