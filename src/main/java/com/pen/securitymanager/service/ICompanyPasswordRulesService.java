package com.pen.securitymanager.service;

import com.pen.securitymanager.db.model.CompanyPasswordRules;
import com.pen.securitymanager.model.DtoCompanyPasswordRules;
import com.pen.securitymanager.model.DtoCompanyPasswordRulesIU;
import com.towpen.base.security.BaseDbService;

public interface ICompanyPasswordRulesService extends BaseDbService<CompanyPasswordRules> {
	public DtoCompanyPasswordRules findCompanyPasswordRulesDto();

	public DtoCompanyPasswordRules updateCompanyPasswordRulesDto(DtoCompanyPasswordRulesIU dtoCompanyPasswordRules);

}
