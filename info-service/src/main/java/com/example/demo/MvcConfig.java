package com.example.demo;

//import com.example.demo.UserInfo.LoginAnnotation.LoginArgumentResolver;
//import com.example.demo.UserInfo.LoginAnnotation.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /*
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginArgumentResolver());
    }

     */


    // 세션이 공유되지 않기때문에 사용자로그인판별을 위한 인터셉터가 의미가 없다 -> 세션 클러스터링 사용
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        registry.addInterceptor(new LoginInterceptor())
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/", "/auth/login", "/auth/logout",
//                        "/css/**", "/*.ico", "/error", "/test", "/auth/sign-up","/auth/id-validation", "/member/register",
//                        "/auth/mail-validation", "/auth/test", "/member/**/name", "/auth/user", "/member/**/my-page");
//    }

}
