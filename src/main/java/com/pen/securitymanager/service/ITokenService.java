package com.pen.securitymanager.service;

import com.towpen.base.security.JWT;
import com.towpen.base.security.model.TOpenSessionInstance;

public interface ITokenService {
    JWT createToken(TOpenSessionInstance instance,Long expireInMinutes,Long expireRefreshTokenInMinutes);

    TOpenSessionInstance parseToken(JWT dtoJWT);
    TOpenSessionInstance parseToken(String jwtToken);
}
