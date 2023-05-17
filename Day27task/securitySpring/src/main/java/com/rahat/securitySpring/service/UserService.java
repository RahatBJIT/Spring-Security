package com.rahat.securitySpring.service;

import com.rahat.securitySpring.model.UserResponeModel;
import com.rahat.securitySpring.model.UserRequestModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<Object> register(UserRequestModel requestModel);

    UserResponeModel getUser(Long id);
    List <UserResponeModel> getUsers();
}
