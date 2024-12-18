package com.pen.securitymanager.repository;

import com.pen.securitymanager.db.model.CompanyPasswordRules;
import com.towpen.base.db.repository.BaseDaoRepository1;

import java.util.Optional;

public interface CompanyPasswordRulesRepository extends BaseDaoRepository1<CompanyPasswordRules> {
	public Optional<CompanyPasswordRules> findByCompanyCode(String companyCode);
}
