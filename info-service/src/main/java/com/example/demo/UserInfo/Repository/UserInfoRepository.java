package com.example.demo.UserInfo.Repository;

import com.example.demo.UserInfo.domain.UserInfo;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public interface UserInfoRepository {


    public UserInfo findUserById(String id);

    public void save(UserInfo userInfo);

    public Boolean existsByEmail(String email);

    public Boolean existsById(String userId);

    @Transactional
    public int delete(String id);
}
