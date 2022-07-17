package com.example.demo.UserInfo.Repository;

import com.example.demo.UserInfo.domain.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
@Primary
@RequiredArgsConstructor
public class UserInfoRepositoryForMSA implements UserInfoRepository{

	private final EntityManager em;

	@Transactional
	public UserInfo findUserById(String id) {
		return em.find(UserInfo.class, id);
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

	@Transactional
	public int delete(String userId) {
		String jpql = "delete from UserInfo u where u.userId = :userId";
		Query query = em.createQuery(jpql).setParameter("userId",userId);
		int rows =query.executeUpdate();
		return rows;
	}


}
