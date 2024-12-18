package com.pen.securitymanager.service.imp;

import com.pen.securitymanager.auth.model.AuthenticationRequest;
import com.pen.securitymanager.auth.model.RefreshTokenRequest;
import com.pen.securitymanager.service.IAuthenticationService;
import com.pen.securitymanager.service.ITokenService;
import com.pen.securitymanager.service.IUserDefService;
import com.towpen.base.security.ISessionInstanceService;
import com.towpen.base.security.JWT;
import com.towpen.base.security.model.TOpenSessionInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImp implements IAuthenticationService {

	public static final Long EXPIRE_IN_MINUTES = 60L;

	public static final Long EXPIRE_REFRESH_TOKEN_IN_MINUTES = 120L;
	@Autowired
	private IUserDefService userDefService;

	@Autowired
	private ITokenService tokenService;

	@Autowired
	private ISessionInstanceService sessionInstanceService;

	@Override
	public JWT authenticate(AuthenticationRequest loginRequestDto) {
		TOpenSessionInstance instance = userDefService.login(loginRequestDto.getUsername(),loginRequestDto.getPassword());
		return null;
	}

	@Override
	public JWT refreshToken(RefreshTokenRequest refreshTokenRequest) {
		return null;
	}

	@Override
	public JWT changeCompany(String company) {
		return null;
	}

	@Override
	public JWT retrieveToken(String token) {
		return null;
	}
}
