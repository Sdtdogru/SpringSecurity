package com.pen.securitymanager.repository;

import com.towpen.base.db.model.security.UserRole;
import com.towpen.base.db.repository.BaseDaoRepository1;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends BaseDaoRepository1<UserRole> {
	@EntityGraph(value = "userRoleDetails", type = EntityGraph.EntityGraphType.FETCH)
	@Query("select ur from UserRole ur where ur.roleDef.Id = :roleDefId")
	Optional<List<UserRole>> findUserRoleDetailsByRoleDefId(String roleDefId);

	Long deleteByRoleDefIdAndUserDefId(String roleDefId, String userDefId);

	Boolean existsByRoleDefIdAndUserDefId(String roleDefId, String userDefId);

	@EntityGraph(value = "userRoleDetails", type = EntityGraph.EntityGraphType.FETCH)
	@Query("select ur from UserRole ur where ur.userDef.id = :userId")
	List<UserRole> findUsersRoleList(String userId);

	@Query(value =  "Select distinct ur.userDef.id from UserRole ur"
			+ " where ur.roleDef.id IN :roleDefIdList")
	List<String> findUserDefIdListByRoleDefIdList(List<String> roleDefIdList);
}
