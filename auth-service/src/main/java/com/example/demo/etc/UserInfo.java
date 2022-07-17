package com.example.demo.etc;

import com.example.demo.UserAccount.domain.UserAccount;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "tbl_user_info")
@Getter
@Setter
@Entity
public class UserInfo {

	@Id
	@Column(name = "user_id")
	private String userId;
	
	private String userName;
	
	private String email;

	@OneToOne(mappedBy = "userInfo", fetch = FetchType.LAZY)
	private UserAccount userAccount;

	public static UserInfo createUserInfo(String userId, String userName, String email) {
		UserInfo userInfo = new UserInfo();
		userInfo.userId = userId;
		userInfo.userName = userName;
		userInfo.email= email;
		return userInfo;
	}


}
