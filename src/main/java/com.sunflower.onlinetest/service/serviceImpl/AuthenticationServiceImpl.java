package com.sunflower.onlinetest.service.serviceImpl;

import com.sunflower.onlinetest.dao.UserDAO;
import com.sunflower.onlinetest.entity.UserEntity;
import com.sunflower.onlinetest.rest.request.LoginRequest;
import com.sunflower.onlinetest.rest.request.SignupRequest;
import com.sunflower.onlinetest.service.AuthenticationService;

import javax.inject.Inject;
import java.util.Objects;

public class AuthenticationServiceImpl implements AuthenticationService {

    public static final String EMAIL_IS_EXISTED = "Email is existed";
    public static final String COULD_NOT_SIGNUP = "Could not signup!";
    public static final String INFORMATION_IS_NOT_VALID = "Information is not valid";
    public static final String EMAIL_OR_PASSWORD_IS_NOT_CORRECT = "Email or password is not correct";

    @Inject
    private UserDAO userDAO;

    @Override
    public UserEntity login(LoginRequest loginRequest) {
        if (loginRequest.getEmail().isEmpty() || loginRequest.getPassword().isEmpty()) {
            throw new RuntimeException(INFORMATION_IS_NOT_VALID);
        }
        UserEntity processingUser = userDAO.findByEmail(loginRequest.getEmail());
        if (Objects.nonNull(processingUser) && processingUser.getPassword().equals(loginRequest.getPassword())) {
            return processingUser;
        } else {
            throw new RuntimeException(EMAIL_OR_PASSWORD_IS_NOT_CORRECT);
        }
    }

    @Override
    public UserEntity signup(SignupRequest signupRequest) {
        if (signupRequest.getFullName().isEmpty() || signupRequest.getEmail().isEmpty() || signupRequest.getPassword().isEmpty()) {
            throw new RuntimeException(INFORMATION_IS_NOT_VALID);
        }
        UserEntity processingUser = userDAO.findByEmail(signupRequest.getEmail());
        if (Objects.nonNull(processingUser)) {
            throw new RuntimeException(EMAIL_IS_EXISTED);
        }
        try {
            UserEntity newUser = UserEntity.builder()
                    .fullName(signupRequest.getFullName())
                    .email(signupRequest.getEmail())
                    .password(signupRequest.getPassword())
                    .build();
            userDAO.save(newUser);
            return newUser;
        } catch (Exception e) {
            throw new RuntimeException(COULD_NOT_SIGNUP, e);
        }
    }
}
