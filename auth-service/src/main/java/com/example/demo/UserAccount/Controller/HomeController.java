package com.example.demo.UserAccount.Controller;

import com.example.demo.UserAccount.API.UserAccountAPI;
import com.example.demo.UserAccount.LoginAnnotation.Login;
import com.example.demo.UserAccount.domain.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserAccountAPI userAccountAPI;

    /**
     * ArgumentResolver(@Login)을 이용하여 로그인 판단
     */
    @GetMapping("/")
    public String home(@Login UserAccount loginMember, Model
            model) {
        //******비회원*********//
        if (loginMember == null) {
            return "home-guest";
        }

        //********회원**********//
        // Member 서버에게 회원이름 요청
        String userName = userAccountAPI.requestName(loginMember.getUserId());
        model.addAttribute("memberId", loginMember.getUserId());
        model.addAttribute("memberName", userName);
        return "home-member";
    }
}
