package com.pen.securitymanager.repository;

import com.pen.securitymanager.db.model.CompanyPasswordRules;
import com.towpen.base.db.repository.BaseDaoRepository;

import java.util.Optional;

public interface CompanyPasswordRulesRepository extends BaseDaoRepository<CompanyPasswordRules> {
	public Optional<CompanyPasswordRules> findByCompanyCode(String companyCode);
}
