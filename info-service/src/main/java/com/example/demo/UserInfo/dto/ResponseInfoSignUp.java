package com.example.demo.UserInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInfoSignUp {
    String userId;
    String userName;
    String email;
}
