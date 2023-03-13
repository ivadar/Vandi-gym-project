package com.softuni.vandi.services;


import com.softuni.vandi.model.entity.Workout;
import com.softuni.vandi.view.WorkoutsScheduleViewModel;

import java.util.List;
import java.util.Optional;

public interface WorkoutService {
    void init();

    List<WorkoutsScheduleViewModel> findAll();
    Optional<Workout> findById(Long id);





}
