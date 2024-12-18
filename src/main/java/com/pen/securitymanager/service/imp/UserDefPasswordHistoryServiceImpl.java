package com.pen.securitymanager.service.imp;

import com.pen.securitymanager.db.model.UserDefPasswordHistory;
import com.pen.securitymanager.repository.UserDefPasswordHistoryRepository;
import com.pen.securitymanager.service.IUserDefPasswordHistoryService;
import com.towpen.base.BaseDbServiceImp;
import com.towpen.base.db.model.security.UserDef;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED)
@Slf4j
public class UserDefPasswordHistoryServiceImpl extends BaseDbServiceImp<UserDefPasswordHistoryRepository, UserDefPasswordHistory> implements IUserDefPasswordHistoryService {

	@Override
	public Class<?> getDTOClassForService() {
		return null;
	}

	@Override
	public List<UserDefPasswordHistory> findByUserDef(UserDef userDef, Integer limit) {
		return dao.findByUserDef(userDef, limit>0?limit:-1);
	}
}
