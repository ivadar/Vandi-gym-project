package com.softuni.vandi.services.impl;


import com.softuni.vandi.model.entity.User;
import com.softuni.vandi.model.enums.UserRoleEnum;
import com.softuni.vandi.model.service.UserServiceModel;
import com.softuni.vandi.repositories.UserRepository;
import com.softuni.vandi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
