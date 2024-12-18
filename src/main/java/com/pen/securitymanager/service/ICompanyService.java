package com.pen.securitymanager.service;

import com.pen.securitymanager.model.DtoCompany;
import com.towpen.base.db.model.security.Company;
import com.towpen.base.security.BaseDbService;

import java.util.List;

public interface ICompanyService extends BaseDbService<Company> {
	Company findByCompanyCode();

	String getCompanyLogoBase64(boolean isSmall);

	List<DtoCompany> getAllCompany();

	List<DtoCompany> getAllCompanyDto();
}
