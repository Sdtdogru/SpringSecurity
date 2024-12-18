package com.pen.securitymanager.controller;

import com.pen.securitymanager.auth.model.AuthenticationRequest;
import com.pen.securitymanager.auth.model.RefreshTokenRequest;
import com.towpen.base.restservice.model.RestRootEntity;
import com.towpen.base.security.JWT;
import org.springframework.web.bind.annotation.RequestBody;

public interface RestAuthenticateController {
	RestRootEntity<JWT> login(@RequestBody AuthenticationRequest auth);

	RestRootEntity<JWT>refresh(@RequestBody RefreshTokenRequest refreshTokenRequest);

	RestRootEntity<JWT>changeCompany(String company);
}
