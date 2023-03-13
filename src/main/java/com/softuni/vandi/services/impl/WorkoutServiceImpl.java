package com.softuni.vandi.services.impl;


import com.softuni.vandi.model.entity.Workout;
import com.softuni.vandi.model.enums.WorkoutEnum;
import com.softuni.vandi.view.WorkoutsScheduleViewModel;
import com.softuni.vandi.repositories.TrainerRepository;
import com.softuni.vandi.repositories.UserRepository;
import com.softuni.vandi.repositories.WorkoutRepository;
import com.softuni.vandi.services.UserService;
import com.softuni.vandi.services.WorkoutService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final TrainerRepository trainerRepository;

    private final UserService userService;
    private final UserRepository userRepository;


    private final ModelMapper modelMapper;



    public WorkoutServiceImpl(WorkoutRepository workoutRepository, TrainerRepository trainerRepository, UserService userService, UserRepository userRepository, ModelMapper modelMapper) {
        this.workoutRepository = workoutRepository;
        this.trainerRepository = trainerRepository;
        this.userService = userService;
        this.userRepository = userRepository;

        this.modelMapper = modelMapper;

    }

    @Override
    public void init() {
        if (this.workoutRepository.count()!=0){
            return;
        }
        Arrays.stream(WorkoutEnum.values())
                .forEach(w->{
                    Workout workout=new Workout();
                    workout.setName(w);
                    switch (w){
                        case GYM -> {
                            workout.setTime(LocalTime.of(9,0));
                            workout.setDescription("intense");
                            workout.setTrainer(this.trainerRepository.findById(3L).get());
                            workout.setPrice(BigDecimal.valueOf(20));
                        }
                        case HIIT -> {
                            workout.setDescription("fast");
                            workout.setTime(LocalTime.of(12,0));
                            workout.setTrainer(this.trainerRepository.findById(2L).get());
                            workout.setPrice(BigDecimal.valueOf(30));
                        }
                        case POLEDANCE -> {
                            workout.setDescription("exotic");
                            workout.setTime(LocalTime.of(15,30));
                            workout.setTrainer(this.trainerRepository.findById(1L).get());
                            workout.setPrice(BigDecimal.valueOf(10));
                        }
                    }
                    this.workoutRepository.saveAndFlush(workout);
                });



    }

    @Override
    public List<WorkoutsScheduleViewModel> findAll() {
        return this.workoutRepository.findAll()
                .stream().map(workout -> this.modelMapper.map(workout,WorkoutsScheduleViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Workout> findById(Long id) {
        return this.workoutRepository.findById(id);
    }

//    @Override
//    public void signUp(Long id) {
//        User user = this.userService.findById(loggedUser.getId());
//        user.getWorkouts().add(this.workoutRepository.findById(id).get());
//        this.userRepository.saveAndFlush(user);
//    }




}
