package com.pen.securitymanager.repository;

import com.pen.securitymanager.db.model.UserDefPasswordHistory;
import com.towpen.base.db.model.security.UserDef;
import com.towpen.base.db.repository.BaseDaoRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDefPasswordHistoryRepository extends com.pen.securitymanager.repository.BaseDaoRepository<UserDefPasswordHistory>, BaseDaoRepository<UserDefPasswordHistory> {
	@Query("select udph from UserDefPasswordHistory udph where udph.userDef=:userDef order by passwordChangeTime desc limit :limit")
	List<UserDefPasswordHistory> findByUserDef(UserDef userDef,Integer limit);

}
