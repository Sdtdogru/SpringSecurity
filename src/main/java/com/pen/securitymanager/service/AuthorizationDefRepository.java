package com.pen.securitymanager.service;

import com.pen.securitymanager.db.model.AuthorizationDef;
import com.towpen.base.db.repository.BaseDaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorizationDefRepository extends BaseDaoRepository<AuthorizationDef> {
	@EntityGraph(value = "authorization-menu", type = EntityGraph.EntityGraphType.FETCH)
	@Query("select ad from AuthorizationDef ad where 1=1 "
			+ "and (:name is null or LOWER(UPPER(ad.name)) like concat('%', LOWER(UPPER(:name)), '%') )"
			+ "and (:name is null or LOWER(UPPER(ad.description)) like concat('%', LOWER(UPPER(:description)), '%') )"
			+ "and (:isActive is null or ad.isActive = :isActive)"
			+ "and (:shortCode is null or LOWER(UPPER(ad.shortCode)) like concat('%', LOWER(UPPER(:shortCode)), '%') )")
	Page<AuthorizationDef> findAuthorizationDefWithSearchParams(Pageable pageable, String name, String description, Boolean isActive, String shortCode);

	@Query(value =  "Select distinct ad from AuthorizationDef ad, RoleAuthorization ra, UserRole ur, RoleDef rd "
			+ " where ad.id = ra.authorizationDef.id "
			+ " and ra.roleDef.id = ur.roleDef.id "
			+ " and rd.id = ur.roleDef.id"
			+ " and rd.isActive = true "
			+ " and ad.isActive = true "
			+ " and ur.userDef.id = :userId", nativeQuery = false)
	List<AuthorizationDef> findAuthorizationDefListByUser(String userId);


	@Query("select case when (count(*) > 0) then true else false end from AuthorizationDef ad where LOWER(UPPER(ad.name)) = LOWER(UPPER(:name))")
	Boolean isAuthorizationDefExistsByExactName(String name);
}
