package com.example.demo.UserAccount.Service;

import com.example.demo.UserAccount.API.UserAccountAPI;
import com.example.demo.UserAccount.Exception.*;
import com.example.demo.UserAccount.Repository.UserAccountRepository;
import com.example.demo.UserAccount.domain.UserAccount;
import com.example.demo.UserAccount.dto.SignUpDto;
import com.example.demo.UserAccount.dto.UserInfoDto;
import com.example.demo.UserAccount.vo.ResponseUserInfo;
import com.example.demo.UserAccount.dto.UserAccountDto;
import com.example.demo.etc.UserInfo;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserAccountService {
	
	private final UserAccountRepository userAccountRepository;

	private final UserAccountAPI userAccountAPI;

	@Transactional
	public void register(SignUpDto dto) {
		try {

			// Member 서버가 DB에 UserInfo Entity를 Insert
			UserInfoDto userInfoDto = userAccountAPI.requestSignUp(dto.getId(), dto.getName(), dto.getEmail());

			// UserAccount를 Insert
			UserAccount userAccount = UserAccount.createUserAccount(dto.getId(), dto.getPassword(), new ModelMapper().map(userInfoDto, UserInfo.class));
			userAccountRepository.save(userAccount);
		} catch (NoConnectionException | InfoServerException e){
			throw new NoSignUpException("member 서버 연결 실패");
			//Transaction RollBack
		} catch (Exception e){
			userAccountAPI.deleteUserInfo(dto.getId());
			throw new NoSignUpException("회원가입 실패");
			//Transaction RollBack
		}
	}

	@Transactional
	public UserAccount login(UserAccountDto dto) {
		try {
			UserAccount user = userAccountRepository.findByUserId(dto.getUserId());
			// 비밀번호 체크
			if (!(dto.getUserPassword().equals(user.getUserPassword()))) {
				throw new LoginException("아이디와 비밀번호가 올바르지 않습니다.");
			}
			user.updateLoginYN("Y");
			userAccountRepository.save(user);
			return user;
		}
		// 일치하는 아이디가 없는경우
		catch (Exception e){
			throw new LoginException("아이디와 비밀번호가 올바르지 않습니다.");
		}
	}

	@Transactional
	public void controlLoginYN(UserAccount userAccount){
		userAccount.updateLoginYN("N");
		userAccountRepository.save(userAccount);
	}

	@Transactional
	public boolean isIdDuplicated(String id) {
		String regExpression = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*$";

		//정규식 검사
		if (!Pattern.matches(regExpression, id)) {
			throw new RuntimeException("아이디가 올바르지 않습니다.");
		}

		return userAccountRepository.existsById(id);
	}




}
