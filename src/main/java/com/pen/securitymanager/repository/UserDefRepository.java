package com.pen.securitymanager.repository;

import com.towpen.base.db.model.security.UserDef;
import com.towpen.base.db.repository.BaseDaoRepository1;

import java.util.Optional;

public interface UserDefRepository extends  BaseDaoRepository1<UserDef> {
	Optional<UserDef> findByUserName(String userName);
}
