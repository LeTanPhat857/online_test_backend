package com.sunflower.onlinetest.service;


import com.sunflower.onlinetest.entity.UserEntity;
import com.sunflower.onlinetest.rest.response.JwtDTO;

public interface JWTAuthenticationService {

     JwtDTO createAuthorizedToken(UserEntity userEntity);

     void checkAuthorizedToken(String authorization);

     Integer getUserId(String authorization);
}
