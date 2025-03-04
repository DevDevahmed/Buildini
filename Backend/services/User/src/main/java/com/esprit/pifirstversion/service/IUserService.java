package com.esprit.pifirstversion.service;


import com.esprit.pifirstversion.dtos.CreateUserRequest;
import com.esprit.pifirstversion.dtos.UpdateUserRequest;
import com.esprit.pifirstversion.dtos.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse createUser(CreateUserRequest request);
    UserResponse getUserById(int id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(int id, UpdateUserRequest request);
    void deleteUser(int id);
}