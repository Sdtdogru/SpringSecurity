package com.pen.securitymanager.repository;

import com.towpen.base.db.model.security.Company;
import com.towpen.base.db.repository.BaseDaoRepository1;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends BaseDaoRepository1<Company> {
	Optional<Company> findByCompanyCode(String companyCode);

	@Query("select n from Company n where n.isMainCompany = 0")
	List<Company> findCompanyListNotMain();

	List<Company> findAll();
}
