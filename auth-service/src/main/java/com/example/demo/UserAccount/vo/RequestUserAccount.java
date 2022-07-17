package com.example.demo.UserAccount.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserAccount {
    String userId;
    String userPassword;
    //boolean error = false;

}
