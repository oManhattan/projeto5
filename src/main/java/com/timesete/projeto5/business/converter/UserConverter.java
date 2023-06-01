package com.timesete.projeto5.business.converter;

import com.timesete.projeto5.model.dto.User.UserRequest;
import com.timesete.projeto5.model.dto.User.UserResponse;
import com.timesete.projeto5.model.entity.UserModel;

import java.util.List;

public class UserConverter {

    public static UserModel toUserModel(UserRequest request) {
        return UserModel.builder()
                .name(request.getName())
                .login(request.getLogin())
                .password(request.getPassword())
                .accessType(request.getAccessType())
                .build();
    }

    public static UserResponse toUserResponse(UserModel model) {
        return UserResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .login(model.getLogin())
                .accessType(model.getAccessType())
                .build();
    }

    public static List<UserResponse> toUserResponseList(List<UserModel> modelList) {
        return modelList.stream().map(UserConverter::toUserResponse).toList();
    }

}
