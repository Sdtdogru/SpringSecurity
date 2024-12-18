package com.pen.securitymanager.service.imp;

import com.pen.securitymanager.db.model.UserDefCompanyAuthLog;
import com.pen.securitymanager.repository.UserDefCompanyAuthLogRepository;
import com.pen.securitymanager.service.IUserDefCompanyAuthLogService;
import com.towpen.base.BaseDbServiceImp;
import com.towpen.base.db.model.security.Company;
import com.towpen.base.db.model.security.UserDef;

public class UserDefCompanyAuthLogServiceImpl extends BaseDbServiceImp<UserDefCompanyAuthLogRepository, UserDefCompanyAuthLog> implements IUserDefCompanyAuthLogService {
	@Override
	public void addUserDefCompanyAuthLog(Company company, UserDef userDef, String process) {
		UserDefCompanyAuthLog log = new UserDefCompanyAuthLog();
		log.setCompany(company);
		log.setUserDef(userDef);
		log.setProcess(process);
		log.setProcessUser(sessionInstanceService.getUserCode());
		save(log);
	}

	@Override
	public Class<?> getDTOClassForService() {
		return null;
	}
}
