package com.example.demo.UserInfo.api;

import com.example.demo.UserInfo.exception.ExceptionControl;
import com.example.demo.UserInfo.exception.NoServerException;
import com.example.demo.UserInfo.exception.NoSignUpException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.Duration;

@RequiredArgsConstructor
@Service
public class UserInfoAPI {

    @Value("${msa.memberAPI}")
    String serverAuthAPI;


    @Transactional
    public Boolean requestSignUp(String userId) {

        WebClient webClient = WebClient.builder().baseUrl(serverAuthAPI).build();

        Boolean result = webClient.post()
                .uri("/auth/{userId}/register", userId)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new NoSignUpException("회원가입 오류입니다")))
                .bodyToMono(Boolean.class)
                .timeout(Duration.ofSeconds(5))
                .onErrorMap(ExceptionControl::ConnectionError, ex -> new NoServerException("해당 서버와 연결 불가능", ex))
                .block();

        return result;
    }
}
