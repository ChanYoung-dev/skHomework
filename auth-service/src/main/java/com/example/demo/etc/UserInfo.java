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
	@Column(name = "user_id", length = 10, nullable = false, unique = true)
	private String userId;

	@Column(length = 10, nullable = false)
	private String userName;

	@Column(length = 100, nullable = false)
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
