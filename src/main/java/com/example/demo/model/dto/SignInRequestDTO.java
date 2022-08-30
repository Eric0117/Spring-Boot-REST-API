package com.example.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
@Data
public class SignInRequestDTO {
    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;
}
