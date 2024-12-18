package com.pen.securitymanager.service;

import com.pen.securitymanager.db.model.UserDefCompanyAuth;
import com.pen.securitymanager.model.DtoUserDefCompanyAuth;
import com.pen.securitymanager.model.DtoUserDefCompanyAuthIU;
import com.towpen.base.security.BaseDbService;

import java.util.List;

public interface IUserDefCompanyAuthService extends BaseDbService<UserDefCompanyAuth> {
	DtoUserDefCompanyAuth saveUserDefCompanyAuth(DtoUserDefCompanyAuthIU input);

	DtoUserDefCompanyAuth deleteUserDefCompanyAuth(String id);

	List<DtoUserDefCompanyAuth> findAuthDefListByParams(String companyId, String userDefId);
}
