package com.example.demo.UserInfo.dto;

import lombok.Data;

@Data
public class ResponseInfoSignUp {
    String userId;
    String userName;
    String email;

    public ResponseInfoSignUp(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    public ResponseInfoSignUp() {
    }
}
