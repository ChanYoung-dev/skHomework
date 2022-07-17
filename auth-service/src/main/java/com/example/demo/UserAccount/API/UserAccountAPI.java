package com.example.demo.UserAccount.API;

import com.example.demo.UserAccount.Exception.*;
import com.example.demo.UserAccount.dto.UserInfoDto;
import com.example.demo.UserAccount.vo.RequestUserInfo;
import com.example.demo.UserAccount.vo.ResponseUserInfo;
import com.example.demo.etc.UserInfo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
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
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new NoUserException("해당 유저는 없습니다")))
                .bodyToMono(ResponseUserInfo.class)
                .timeout(Duration.ofSeconds(5))
                .onErrorMap(ExceptionControl::ConnectionError, ex -> new NoUserException("연결시간초과", ex))
                .block();

        return result.getUserName();
    }

    @Transactional
    public UserInfoDto requestSignUp(String userId, String userName, String email) {

        RequestUserInfo dto = new RequestUserInfo(userId, userName, email);

        WebClient webClient = WebClient.builder().baseUrl(serverMemberAPI).build();

        ResponseUserInfo result = webClient.post()
                .uri("/member/register", userId)
                .body(Mono.just(dto), RequestUserInfo.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new InfoServerException("잘못된 요청입니다")))
                .onStatus(HttpStatus::is5xxServerError, error -> Mono.error(new InfoServerException("Info 서버에 문제가 있습니다")))
                .bodyToMono(ResponseUserInfo.class)
                .timeout(Duration.ofSeconds(5))
                .onErrorMap(ExceptionControl::ConnectionError, ex -> new NoConnectionException("연결시간초과", ex))
                .block();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return mapper.map(result, UserInfoDto.class);
    }

    @Transactional
    public void deleteUserInfo(String userId) {


        WebClient webClient = WebClient.builder().baseUrl(serverMemberAPI).build();

        webClient.post()
                .uri("/member/delete/{userId}", userId)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new InfoServerException("잘못된 요청입니다")))
                .onStatus(HttpStatus::is5xxServerError, error -> Mono.error(new InfoServerException("Info 서버에 문제가 있습니다")))
                .bodyToMono(ResponseUserInfo.class)
                .timeout(Duration.ofSeconds(5))
                .onErrorMap(ExceptionControl::ConnectionError, ex -> new NoConnectionException("연결시간초과", ex))
                .block();
    }
}
