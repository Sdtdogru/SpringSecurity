package com.pen.securitymanager.repository;

import com.pen.securitymanager.db.model.LoginAttempt;
import com.towpen.base.db.repository.BaseDaoRepository1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface LoginAttemptRepository extends BaseDaoRepository<LoginAttempt>, BaseDaoRepository1<LoginAttempt> {
	@Query("select la from LoginAttempt la  where 1=1 and (:userName is null or la.userName LIKE concat('%', :userName, '%'))"
			+ " and (:ipAddress is null or la.ipAddress like concat('%',:ipAddress, '%') )"
			+ " and (:isLoginSuccess is null or la.isLoginSuccess=:isLoginSuccess) "
			+ " and (:createTime is null or la.createTime=:createTime) "
			+ " and (:startAccessTime is null or la.createTime>=:startAccessTime)"
			+ " and (:endAccessTime is null or la.createTime<=:endAccessTime)"
			+ " and (:errorMessage is null or la.errorMessage=:errorMessage)"
			+ " and (:sessionId is null or la.sessionId=:sessionId)")
	Page<LoginAttempt> findLoginAttemptsWithSearchParams(Pageable pageable, String userName, String ipAddress, Date createTime, Date startAccessTime, Date endAccessTime, Boolean isLoginSuccess, String errorMessage, String sessionId);

}
