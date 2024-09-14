package org.itbuddy.security.auth.mapper;

import org.itbuddy.security.auth.application.request.RegisterRequest;
import org.itbuddy.security.common.security.repository.entity.UserEntity;

public class UserEntityMapper {


    public static UserEntity of(RegisterRequest request, String encodedPassword){
        return UserEntity.builder()
                         .firstname(request.firstname())
                         .lastname(request.lastname())
                         .email(request.email())
                         .password(encodedPassword)
                         .role(request.role())
                         .build();
    }
}
