package com.pen.securitymanager.service.imp;

import com.towpen.utils.RandomUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class SessionManager {
	private AtomicLong counter;

	private String referenceNumber;

	@PostConstruct
	public void initialize() {
		counter=new AtomicLong(1L);
		this.referenceNumber= RandomUtil.createSessionKey();
		log.info("Session manager object created");
		log.info("Session manager default sessionId:{}",referenceNumber);
	}

	public String createSessionId() {
		return this.referenceNumber+"-"+counter.incrementAndGet();
	}
}

