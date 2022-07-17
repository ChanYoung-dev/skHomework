package com.example.demo.UserInfo.controller;


import com.example.demo.UserInfo.Repository.UserInfoRepository;
import com.example.demo.UserInfo.Service.UserInfoService;
import com.example.demo.UserInfo.domain.UserInfo;
import com.example.demo.UserInfo.vo.RequestInfoSignUp;
import com.example.demo.UserInfo.vo.ResponseInfoSignUp;
import com.example.demo.UserInfo.vo.ResponseUserInfo;
import com.example.demo.UserInfo.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    
    private final UserInfoRepository userInfoRepository;

    private final UserInfoService userInfoService;
    @Value("${msa.domain}")
    String serverDomain;

    @GetMapping("/healthcheck")
    @ResponseBody
    public String test() {
        return "info-service working..";
    }


    @GetMapping("/{userId}/my-page")
    public String infoByMSA(@PathVariable String userId, Model model) {
        /**
         * 사용자 정보 찾는 방법
         * 1. 단순하게 UserInfo에서 정보 값 꺼내오기
         * 2. UserInfo와 UserAccount를 Join을 통하여 정보 꺼내오기
         * 인터페이스를 통하여 각 구현
         */
        UserInfo userInfo = userInfoRepository.findUserById(userId);
        model.addAttribute("serverDomain", serverDomain);
        model.addAttribute("memberInfo", new ModelMapper().map(userInfo, UserInfoDto.class));
        return "my-page";
    }

    @GetMapping("/{userId}/name")
    @ResponseBody
    public ResponseEntity<ResponseUserInfo> findUserName(@PathVariable String userId){
        UserInfo userInfo = userInfoRepository.findUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseUserInfo.builder().userName(userInfo.getUserName()).build()
        );
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<ResponseInfoSignUp> registerInfo(@RequestBody RequestInfoSignUp dto){

        UserInfo userInfo = userInfoService.register(dto.getUserId(), dto.getUserName(), dto.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(new ModelMapper().map(userInfo, ResponseInfoSignUp.class));
    }

    @PostMapping("/delete/{userId}")
    @ResponseBody
    public ResponseEntity<ResponseInfoSignUp> deleteUserInfo(@PathVariable String userId){
        userInfoRepository.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
