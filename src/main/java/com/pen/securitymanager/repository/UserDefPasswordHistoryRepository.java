package com.pen.securitymanager.repository;

import com.pen.securitymanager.db.model.UserDefPasswordHistory;
import com.towpen.base.db.model.security.UserDef;
import com.towpen.base.db.repository.BaseDaoRepository1;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDefPasswordHistoryRepository extends BaseDaoRepository<UserDefPasswordHistory>, BaseDaoRepository1<UserDefPasswordHistory> {
	@Query("select udph from UserDefPasswordHistory udph where udph.userDef=:userDef order by passwordChangeTime desc limit :limit")
	List<UserDefPasswordHistory> findByUserDef(UserDef userDef,Integer limit);

}
