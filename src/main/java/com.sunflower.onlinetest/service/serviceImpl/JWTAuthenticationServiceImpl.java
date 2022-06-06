package com.sunflower.onlinetest.service.serviceImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sunflower.onlinetest.config.AppConfig;
import com.sunflower.onlinetest.dao.UserDAO;
import com.sunflower.onlinetest.entity.UserEntity;
import com.sunflower.onlinetest.rest.response.JwtDTO;
import com.sunflower.onlinetest.service.JWTAuthenticationService;
import com.sunflower.onlinetest.util.CustomBase64;

import javax.inject.Inject;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class JWTAuthenticationServiceImpl implements JWTAuthenticationService {

    @Inject
    private UserDAO userDAO;

    public JwtDTO createAuthorizedToken(UserEntity userEntity) {
        String token = null;
        String secretKey = AppConfig.SECRET_KEY;
        String issuer = AppConfig.ISSUER;
        int timeToLive = AppConfig.TIME_TO_LIVE;
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            token = com.auth0.jwt.JWT.create()
                    .withIssuer(issuer)
                    .withJWTId(UUID.randomUUID().toString())
                    .withClaim("id", CustomBase64.encode(String.valueOf(userEntity.getId())))
                    .withClaim("email", userEntity.getEmail())
                    .withExpiresAt(this.setTokenTimeToLive(timeToLive))
                    .sign(algorithm);
        } catch (Exception exception) {
            throw new RuntimeException("when call JWTAuthenticationServiceImpl.createAuthorizedToken() with user email: " + userEntity.getEmail() + " -> " + exception.getMessage());
        }
        return new JwtDTO(token, timeToLive);
    }

    public void checkAuthorizedToken(String authorization) {
        if (Objects.isNull(authorization)) {
            throw new RuntimeException("Authorization token is null");
        }
        String jwtToken = getJwtToken(authorization);
        try {
            String secretKey = AppConfig.SECRET_KEY;
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm)
                    .withIssuer(AppConfig.ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(jwtToken);
        } catch (JWTVerificationException | IllegalArgumentException exception) {
            throw new RuntimeException("Authorization token is not valid");
        }
    }

    @Override
    public Integer getUserId(String authorization) {
        try {
            String idString = JWT.decode(getJwtToken(authorization)).getClaim("id").asString();
            return CustomBase64.decodeAsInteger(idString);
        } catch (Exception exception) {
            throw new RuntimeException("when call JWTAuthenticationServiceImpl.getUserId() with authorization: " + authorization + " -> " + exception.getMessage());
        }
    }

    private Date setTokenTimeToLive(int timeToLive) {
        return new Date(System.currentTimeMillis() + timeToLive);
    }

    private String getJwtToken(String authorization) {
        String[] authParts = authorization.split("\\s+");
        if (authParts.length < 2 || !"Bearer".equals(authParts[0])) {
            throw new RuntimeException("Authorization token is not valid");
        }
        return authParts[1];
    }
}
