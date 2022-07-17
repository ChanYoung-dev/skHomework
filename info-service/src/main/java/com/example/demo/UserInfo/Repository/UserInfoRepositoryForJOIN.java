package com.example.demo.UserInfo.Repository;

import javax.persistence.EntityManager;

import com.example.demo.UserInfo.domain.UserInfo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Repository
//@Primary
@RequiredArgsConstructor
public class UserInfoRepositoryForJOIN implements UserInfoRepository{

	private final EntityManager em;

	@Transactional
	public UserInfo findUserById(String id) {
		return em.createQuery("select i from UserAccount u join u.userInfo i where u.userInfo.id = :userId", UserInfo.class).setParameter("userId", id).getSingleResult();
	}

	public void save(UserInfo userInfo) {
		em.persist(userInfo);
	}

	public Boolean existsByEmail(String email) {
		String qlString = "select case when (count(i) > 0) then true else false end from UserInfo i where i.email = :email";
		return em.createQuery(qlString, Boolean.class).setParameter("email", email).getSingleResult();
	}

	public Boolean existsById(String userId) {
		String qlString = "select case when (count(i) > 0) then true else false end from UserInfo i where i.userId = :userId";
		return em.createQuery(qlString, Boolean.class).setParameter("userId", userId).getSingleResult();
	}


}
