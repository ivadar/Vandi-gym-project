package com.softuni.vandi.services;


import com.softuni.vandi.model.entity.User;
import com.softuni.vandi.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);



    User findById(Long id);






}
