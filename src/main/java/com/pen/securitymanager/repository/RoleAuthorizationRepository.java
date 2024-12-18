package com.pen.securitymanager.repository;

import com.pen.securitymanager.db.model.RoleAuthorization;
import com.towpen.base.db.repository.BaseDaoRepository1;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleAuthorizationRepository extends BaseDaoRepository1<RoleAuthorization> {
	@EntityGraph(value = "roleAuthorizationDetails", type = EntityGraph.EntityGraphType.FETCH)
	@Query("select ra from RoleAuthorization ra where ra.roleDef.Id = :roleDefId")
	Optional<List<RoleAuthorization>> findRoleAuthorizationDetailsByRoleDefId(String roleDefId);

	@Query(value =  "Select ra.roleDef.id from RoleAuthorization ra"
			+ " where ra.authorizationDef.id = :authorizationDefId")
	List<String> findRoleDefIdListByAuthorizationDefId(String authorizationDefId);

	Long deleteByRoleDefIdAndAuthorizationDefId(String roleDefId, String authorizationDefId);

	Long deleteByRoleDefId(String roleDefId);

	Boolean existsByRoleDefIdAndAuthorizationDefId(String roleDefId, String authorizationDefId);

	@Modifying
	@Query("Delete from RoleAuthorization ra where ra.authorizationDef.id = :authorizationDefId and  ra.roleDef.id in :roleDefIdList")
	void deleteRoleAuthorizationByRoleDefIdInAndAuthorizationDefId(@Param("roleDefIdList") List<String> roleDefIdList, @Param("authorizationDefId") String authorizationDefId);

}
