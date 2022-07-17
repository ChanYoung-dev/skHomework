package com.example.demo.UserAccount.domain;

import javax.persistence.*;

import com.example.demo.etc.UserInfo;
import lombok.Getter;
import lombok.Setter;

@Table(name = "tbl_user_account")
@Getter
@Setter
@Entity
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 10, unique = true)
	private String userId; // 사용자 아이디

	@Column(length = 10, nullable = false)
	private String userPassword;


	@Column(length = 1)
	private String loginYN;


	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(
			name = "userId",
			insertable = false,
			updatable = false
	)
	private UserInfo userInfo;

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
		userInfo.setUserAccount(this);
	}



	public static UserAccount createUserAccount(String userId, String userPassword, UserInfo userInfo) {
		UserAccount userAccount = new UserAccount();
		userAccount.userId = userId;
		userAccount.userPassword= userPassword;
		userAccount.setUserInfo(userInfo);
		userAccount.loginYN = "N";
		return userAccount;
	}

	public void updateLoginYN(String loginYN) {
		this.loginYN = loginYN;
		System.out.println("loginYN = " + loginYN);
	}



}

