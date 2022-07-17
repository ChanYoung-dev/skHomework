package com.example.demo.UserAccount.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestUserInfo {

    String userId;
    String userName;
    String email;

    public RequestUserInfo(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }
}
