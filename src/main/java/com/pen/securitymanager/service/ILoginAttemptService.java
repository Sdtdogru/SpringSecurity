package com.pen.securitymanager.service;

import com.pen.securitymanager.db.model.LoginAttempt;
import com.towpen.base.security.BaseDbService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ILoginAttemptService extends BaseDbService<LoginAttempt> {
	Page<LoginAttempt> findLoginAttemptsWithSearchParams(Pageable pageable, String userName, String ipAddress, Date createTime, String startAccessTime, String endAccessTime, Boolean isLoginSuccess, String errorMessage, String sessionId);
}