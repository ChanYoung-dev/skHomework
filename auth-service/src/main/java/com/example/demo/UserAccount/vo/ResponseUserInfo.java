package com.example.demo.UserAccount.vo;

import lombok.Data;

@Data
public class ResponseUserInfo {

    String userId;
    String userName;
    String email;

    public ResponseUserInfo(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    public ResponseUserInfo() {
    }
}
