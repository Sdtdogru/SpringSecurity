package com.pen.securitymanager.service;

import com.pen.securitymanager.auth.model.AuthenticationRequest;
import com.pen.securitymanager.auth.model.RefreshTokenRequest;
import com.towpen.base.security.JWT;

public interface IAuthenticationService {

	public JWT authenticate( AuthenticationRequest loginRequestDto);

	public JWT refreshToken(RefreshTokenRequest refreshTokenRequest);

	public JWT changeCompany(String company);

	public JWT retrieveToken( String token );
}
