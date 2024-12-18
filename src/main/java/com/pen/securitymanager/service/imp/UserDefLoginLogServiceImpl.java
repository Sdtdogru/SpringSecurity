package com.pen.securitymanager.service.imp;

import com.pen.securitymanager.db.model.UserDefLoginLog;
import com.pen.securitymanager.repository.UserDefLoginLogRepository;
import com.pen.securitymanager.service.IUserDefLoginLogService;
import com.towpen.base.security.BaseDbServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
@Slf4j
public class UserDefLoginLogServiceImpl  extends BaseDbServiceImp<UserDefLoginLogRepository, UserDefLoginLog> implements IUserDefLoginLogService {

	@Override
	public Class<?> getDTOClassForService() {
		return null;
	}
}
