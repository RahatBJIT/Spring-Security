package com.rahat.securitySpring.service.impl;


import com.rahat.securitySpring.entity.UserEntity;
import com.rahat.securitySpring.model.UserResponeModel;
import com.rahat.securitySpring.model.UserRequestModel;
import com.rahat.securitySpring.repository.UserRepository;
import com.rahat.securitySpring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return null;
    }

    @Override
    public ResponseEntity<Object> register(UserRequestModel requestModel) {
        UserEntity userEntity = UserEntity.builder()
                .email(requestModel.getEmail())
                .userName(requestModel.getUserName())
                .firstName(requestModel.getFirstName())
                .lastName(requestModel.getLastName())
                .password(requestModel.getPassword())
                .build();
        UserEntity savedUserEntity = userRepository.save(userEntity);

        UserResponeModel savedUserModel = new UserResponeModel( savedUserEntity.getUserName(), savedUserEntity.getEmail(), savedUserEntity.getFirstName());

        return new ResponseEntity<>(savedUserModel, HttpStatus.CREATED);
    }

    @Override
    public UserResponeModel getUser(Long id) {
        Optional<UserEntity> userEntity= userRepository.findById(id);


        UserResponeModel userResponeModel = new UserResponeModel( userEntity.get().getUserName(), userEntity.get().getEmail(), userEntity.get().getFirstName());

        return userResponeModel;
    }

    @Override
    public List<UserResponeModel> getUsers() {
        List<UserEntity> ListOfUser=  userRepository.findAll();
        List<UserResponeModel> responseUsers= new ArrayList<>();
        for(UserEntity element : ListOfUser)
        {
            UserResponeModel userResponeModel = new UserResponeModel( element.getUserName(),element.getEmail(), element.getFirstName());
            responseUsers.add(userResponeModel);


        }



        return responseUsers;
    }


//    @Override
//    public List<UserModel> getUsers() {
//       List<UserModel> List= (List<UserModel>) userRepository.findAll();
//        return (List<UserModel>) ;;
//    }

}
