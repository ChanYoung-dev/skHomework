package com.example.demo.UserAccount.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RequestUserAccount {
    String userId;
    String userPassword;
    //boolean error = false;

    public RequestUserAccount(String id, String password) {
        this.userId = id;
        this.userPassword = password;
        //this.error = error;
    }
}
