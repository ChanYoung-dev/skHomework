package com.example.demo.UserAccount.API;

import com.example.demo.UserAccount.Exception.ExceptionControl;
import com.example.demo.UserAccount.Exception.NoSignUpException;
import com.example.demo.UserAccount.Exception.NoUserException;
import com.example.demo.UserAccount.vo.RequestUserInfo;
import com.example.demo.UserAccount.vo.ResponseUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.Duration;

@RequiredArgsConstructor
@Service
public class UserAccountAPI {

    @Value("${msa.memberAPI}")
    String serverMemberAPI;


    @Transactional
    public String requestName(String userId) {

        WebClient webClient = WebClient.builder().baseUrl(serverMemberAPI).build();

        ResponseUserInfo result = webClient.get()
                .uri("/member/{userId}/name", userId)
                .header(HttpHeaders.SET_COOKIE,"JSESSIONID","")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new NoUserException("해당 유저는 없습니다")))
                .bodyToMono(ResponseUserInfo.class)
                .timeout(Duration.ofSeconds(5))
                .onErrorMap(ExceptionControl::ConnectionError, ex -> new NoUserException("연결시간초과", ex))
                .block();
        System.out.println("result.getName() = " + result.getUserName());

        return result.getUserName();
    }

    @Transactional
    public ResponseUserInfo requestSignUp(String userId, String userName, String email) {

        RequestUserInfo dto = new RequestUserInfo(userId, userName, email);


        WebClient webClient = WebClient.builder().baseUrl(serverMemberAPI).build();

        ResponseUserInfo result = webClient.post()
                .uri("/member/register", userId)
                .body(Mono.just(dto), RequestUserInfo.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new NoSignUpException("해당 유저는 없습니다")))
                .bodyToMono(ResponseUserInfo.class)
                .timeout(Duration.ofSeconds(5))
                .onErrorMap(ExceptionControl::ConnectionError, ex -> new NoUserException("연결시간초과", ex))
                .block();

        return result;
    }
}
