package com.example.demo.UserAccount.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestSignUp {
        String id;
        String email;
        String name;
        String password;
}
