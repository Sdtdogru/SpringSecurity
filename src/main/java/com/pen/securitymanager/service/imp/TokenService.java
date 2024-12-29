package com.pen.securitymanager.service.imp;

import com.google.gson.Gson;
import com.pen.securitymanager.service.ITokenService;
import com.towpen.base.security.BaseDbServiceImp;
import com.towpen.base.security.JWT;
import com.towpen.base.security.model.TOpenSessionInstance;
import com.towpen.utils.TDateUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.management.ObjectName;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService implements ITokenService {

    private static final String KEY_SESSION="sessionInstance";
    private static final String TOKEN_TYPE ="tokenTyep";
    private static final String TOKEN_TYPE_REFRESH ="new";

    private Key hmacKey;
    private Gson gson;
    @Override
    public JWT createToken(TOpenSessionInstance instance,Long expireInMinutes, Long expireRefreshTokenInMinutes) {
        Map<String,Object> claim = new HashMap<>();
        claim.put(KEY_SESSION,toJson(instance));
        return createToken(claim,instance.getUserInformation().getUserName(),expireInMinutes,expireRefreshTokenInMinutes,instance);
    }

    private String toJson(TOpenSessionInstance instance) {
        return gson.toJson(instance);
    }

    @Override
    public TOpenSessionInstance parseToken(JWT dtoJWT) {
        return null;
    }

    @Override
    public TOpenSessionInstance parseToken(String jwtToken) {
        return null;
    }

    private JWT createToken(Map<String,Object> claims,String subject,Long expiresMinutes,Long expireRefreshTokenInMinutes,TOpenSessionInstance instance){
        JWT dtoJWT = new JWT();

        LocalDateTime acessTokenExpiretionDateTime = LocalDateTime.now();
        acessTokenExpiretionDateTime.plusMinutes(expiresMinutes);
        dtoJWT.setAccessTokenExpiration(acessTokenExpiretionDateTime);

        Map<String,Object> tokenClaimMap = new HashMap<>(claims);
        tokenClaimMap.put(TOKEN_TYPE,TOKEN_TYPE_REFRESH);

        String accessToken = Jwts.builder().setClaims(tokenClaimMap).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(TDateUtils.asDateFromLocalDateTime(acessTokenExpiretionDateTime)).signWith(hmacKey).compact();
        dtoJWT.setAccessToken(accessToken);
        dtoJWT.setAccessTokenExpireIn(expiresMinutes);

        LocalDateTime refreshTokenExpiretionDateTime = LocalDateTime.now();
        acessTokenExpiretionDateTime.plusMinutes(expireRefreshTokenInMinutes);

        String refreshToken = Jwts.builder().setClaims(tokenClaimMap).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(TDateUtils.asDateFromLocalDateTime(refreshTokenExpiretionDateTime)).signWith(hmacKey).compact();

        dtoJWT.setRefreshToken(refreshToken);
        dtoJWT.setSessionId(instance.getUserInformation().getSessionId());
        dtoJWT.setRefreshTokenExpiration(refreshTokenExpiretionDateTime);
        dtoJWT.setRefreshTokenexpireIn(expireRefreshTokenInMinutes);


        return dtoJWT;
    }
}
