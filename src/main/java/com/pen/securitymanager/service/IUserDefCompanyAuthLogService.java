package com.pen.securitymanager.service;

import com.pen.securitymanager.db.model.UserDefCompanyAuthLog;
import com.towpen.base.db.model.security.Company;
import com.towpen.base.db.model.security.UserDef;
import com.towpen.base.security.BaseDbService;

public interface IUserDefCompanyAuthLogService extends BaseDbService<UserDefCompanyAuthLog> {
	void addUserDefCompanyAuthLog(Company company, UserDef userDef, String process);
}
