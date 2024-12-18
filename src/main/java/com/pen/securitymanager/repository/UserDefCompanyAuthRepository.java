package com.pen.securitymanager.repository;

import com.pen.securitymanager.db.model.UserDefCompanyAuth;
import com.towpen.base.db.repository.BaseDaoRepository1;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserDefCompanyAuthRepository  extends BaseDaoRepository1<UserDefCompanyAuth>{
	Optional<UserDefCompanyAuth> findByCompanyIdAndUserDefId(String companyId, String userDefId);

	@EntityGraph(value = "user-def-company-auth-entity", type = EntityGraph.EntityGraphType.FETCH)
	@Query("select n from UserDefCompanyAuth n where 1=1 "
			+ " and (:companyId is null or n.company.id = :companyId)"
			+ " and (:userDefId is null or n.userDef.id = :userDefId)")
	List<UserDefCompanyAuth> findByParams(String companyId, String userDefId);
}
