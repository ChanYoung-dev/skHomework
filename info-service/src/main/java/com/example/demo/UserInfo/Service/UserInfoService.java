package com.example.demo.UserInfo.Service;

import com.example.demo.UserInfo.Repository.UserInfoRepository;
import com.example.demo.UserInfo.Repository.UserInfoRepositoryForJOIN;
import com.example.demo.UserInfo.Repository.UserInfoRepositoryForMSA;
import com.example.demo.UserInfo.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoService {
	
	private final UserInfoRepository userInfoRepository;

	@Transactional
	public UserInfo register(String userId, String name, String email) {
		UserInfo userInfo = UserInfo.createUserInfo(userId, name, email);
		userInfoRepository.save(userInfo);
		return userInfo;
	}

}
