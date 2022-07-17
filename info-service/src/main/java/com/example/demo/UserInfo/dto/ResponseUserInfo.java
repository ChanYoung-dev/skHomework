package com.example.demo.UserInfo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUserInfo {
    private String userId; // 사용자 식별자
    private String userName;

    public ResponseUserInfo() {
    }

    public ResponseUserInfo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
