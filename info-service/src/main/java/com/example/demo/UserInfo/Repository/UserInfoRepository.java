package com.example.demo.UserInfo.Repository;

import com.example.demo.UserInfo.domain.UserInfo;

import javax.persistence.EntityManager;


public interface UserInfoRepository {


    public UserInfo findUserById(String id);

    public void save(UserInfo userInfo);

    public Boolean existsByEmail(String email);

    public Boolean existsById(String userId);
}
