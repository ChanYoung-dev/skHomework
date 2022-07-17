package com.example.demo.UserInfo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserInfoDto {

    String email;

    String name;


    public UserInfoDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
