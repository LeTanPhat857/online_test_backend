package com.sunflower.onlinetest.dao;

import com.sunflower.onlinetest.entity.UserEntity;

public class UserDAO extends GenericDAO<UserEntity> {

    public UserEntity findByEmail(String email) {
        try {
            return createTypeQuery("from UserEntity user where user.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception exception) {
            return null;
        }
    }
}
