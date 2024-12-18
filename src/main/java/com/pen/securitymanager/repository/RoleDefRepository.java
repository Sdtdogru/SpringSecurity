package com.pen.securitymanager.repository;

import com.towpen.base.db.model.security.RoleDef;
import com.towpen.base.db.repository.BaseDaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleDefRepository extends BaseDaoRepository<RoleDef> {
	@Query("select rd from RoleDef rd where 1=1 "
			+ "and (:name is null or LOWER(UPPER(rd.name)) like concat('%' , LOWER(UPPER(:name)) , '%')) "
			+ "and (:description is null or LOWER(UPPER(rd.description)) like concat('%' , LOWER(UPPER(:description)) , '%')) "
			+ "and (:isActive is null or rd.isActive = :isActive) "
			+ "and (:isSystemRole is null or rd.isSystemRole = :isSystemRole)")
	Page<RoleDef> findRoleDefWithSearchParams(Pageable pageable, String name, String description, Boolean isActive, Boolean isSystemRole);

	@Query("select case when (count(*) > 0)  then true else false end from RoleDef rd where LOWER(UPPER(rd.name)) = LOWER(UPPER(:name))")
	Boolean isRoleDefExistsByExactName(String name);

	@Query(value =  "Select rd from RoleDef rd"
			+ " where rd.id IN :roleDefIdList")
	List<RoleDef> findRoleDefByRoleDefId(List<String> roleDefIdList);
}
