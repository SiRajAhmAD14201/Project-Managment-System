package com.bluebarry.project_managment_sytem.service.interfaces;

import com.bluebarry.project_managment_sytem.dto.request.UserRequest;
import com.bluebarry.project_managment_sytem.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUser();
    UserResponse getUserById(Long id);
    UserResponse saveUser(UserRequest userRequest);
    UserResponse updateUser(Long id,UserRequest userRequest);
    void deleteUser (Long id);
}
