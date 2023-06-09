package com.softuni.vandi.services.impl;


import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softuni.vandi.model.entity.User;
import com.softuni.vandi.model.service.UserServiceModel;
import com.softuni.vandi.repositories.UserRepository;
import com.softuni.vandi.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;




    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;

    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);

        // TODO (gg): Is there an automatic way to do this? Seems hacky to do explicitly here.
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(userServiceModel.getPassword()));


        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username,password).map(user -> this.modelMapper.map(user,UserServiceModel.class)).orElse(null);
    }




    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).get();
    }



//    @Override
//    public List<SignedUpWorkoutsOfUser> currentWorkouts() {
//        Optional<User> user = this.userRepository.findById(loggedUser.getId());
//      return user.get().getWorkouts().stream()
//              .map(w-> this.modelMapper.map(w, SignedUpWorkoutsOfUser.class))
//              .collect(Collectors.toList());
//    }


}
