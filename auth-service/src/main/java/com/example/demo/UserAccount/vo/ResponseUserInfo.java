package com.example.demo.UserAccount.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseUserInfo {

    String userId;
    String userName;
    String email;

    public ResponseUserInfo(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

}
