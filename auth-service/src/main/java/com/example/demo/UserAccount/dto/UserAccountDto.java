package com.example.demo.UserAccount.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAccountDto {
    private Long id;

    private String userId;

    private String userPassword;

    private String loginYN;
}
