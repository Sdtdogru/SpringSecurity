package com.pen.securitymanager.service;

import com.pen.securitymanager.db.model.UserDefPasswordHistory;
import com.towpen.base.db.model.security.UserDef;
import com.towpen.base.security.BaseDbService;

import java.util.List;

public interface IUserDefPasswordHistoryService extends BaseDbService<UserDefPasswordHistory> {
	List<UserDefPasswordHistory> findByUserDef(UserDef userDef,Integer limit);
}
