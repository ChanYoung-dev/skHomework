package com.example.demo.UserAccount.Repository;

import javax.persistence.EntityManager;

import com.example.demo.UserAccount.domain.UserAccount;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserAccountRepository {

	private final EntityManager em;

	public void save(UserAccount userAccount) {
		if(userAccount.getUserId()==null){
			em.persist(userAccount);
		}
		else{
			em.merge(userAccount);
		}

	}

	public UserAccount findByUserId(String userId){
		return em.createQuery("select u from UserAccount u where u.userInfo.id = :userId", UserAccount.class).setParameter("userId", userId).getSingleResult();
	}

	public UserAccount findUser(Long id) {
		return em.find(UserAccount.class, id);
	}

	public Boolean existsById(String userId) {
		String qlString = "select case when (count(u) > 0) then true else false end from UserAccount u where u.userId = :userId";
		return em.createQuery(qlString, Boolean.class).setParameter("userId", userId).getSingleResult();
	}




}
