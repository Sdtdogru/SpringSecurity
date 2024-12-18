package com.pen.securitymanager.service;

import com.towpen.base.security.model.TOpenSessionInstance;

public interface IUserDefService {
	TOpenSessionInstance login(String userName,String password);
}
