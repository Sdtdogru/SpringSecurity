package com.pen.securitymanager.service;

import com.pen.securitymanager.model.DtoUserDefAccess;
import com.pen.securitymanager.model.DtoUserDefAccessIU;
import com.towpen.base.db.model.security.UserDef;
import com.towpen.base.db.model.security.UserDefAccess;
import com.towpen.base.security.BaseDbService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserDefAccessService extends BaseDbService<UserDefAccess> {

	Optional<UserDefAccess> findByUserDef(UserDef userDef);

	Page<UserDefAccess> findByParams(Pageable pageable, String username, String userDisplayname, Boolean isActive);

	DtoUserDefAccess updateUserDefAccess(String id, DtoUserDefAccessIU input);
}
