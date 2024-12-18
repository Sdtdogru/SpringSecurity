package com.pen.securitymanager.controller.imp;

import com.pen.securitymanager.auth.model.AuthenticationRequest;
import com.pen.securitymanager.auth.model.RefreshTokenRequest;
import com.pen.securitymanager.controller.RestAuthenticateController;
import com.pen.securitymanager.service.IAuthenticationService;
import com.towpen.base.restservice.model.RestRootEntity;
import com.towpen.base.security.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthenticateControllerImp implements RestAuthenticateController {


	@Autowired
	private IAuthenticationService authService;

	@Override
	public RestRootEntity<JWT> login(AuthenticationRequest auth) {
		return RestRootEntity.ok(authService.authenticate(auth));
	}

	@Override
	public RestRootEntity<JWT> refresh(RefreshTokenRequest refreshTokenRequest) {
		return null;
	}

	@Override
	public RestRootEntity<JWT> changeCompany(String company) {
		return null;
	}
}
