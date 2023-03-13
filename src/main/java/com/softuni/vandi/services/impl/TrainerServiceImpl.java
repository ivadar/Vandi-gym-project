package com.softuni.vandi.services.impl;

import com.softuni.vandi.model.entity.Trainer;
import com.softuni.vandi.repositories.TrainerRepository;
import com.softuni.vandi.repositories.WorkoutRepository;
import com.softuni.vandi.services.TrainerService;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository trainerRepository;
    private final WorkoutRepository workoutRepository;

    private static final Trainer trainerHiit = new Trainer();
    private static final Trainer trainerPole = new Trainer();
    private static final Trainer trainerGym = new Trainer();

    public TrainerServiceImpl(TrainerRepository trainerRepository, WorkoutRepository workoutRepository) {
        this.trainerRepository = trainerRepository;
        this.workoutRepository = workoutRepository;
    }

    @Override
    public void init() {
        if (trainerRepository.count() != 0) {
            return;
        }

        trainerHiit.setFirstName("Iva");
        trainerHiit.setLastName("Darski");
        trainerRepository.saveAndFlush(trainerHiit);


        trainerPole.setFirstName("Nadia");
        trainerPole.setLastName("Nadia");
        trainerRepository.saveAndFlush(trainerPole);


        trainerGym.setFirstName("Pesho");
        trainerGym.setLastName("Peshev");
        trainerRepository.saveAndFlush(trainerGym);


    }
}
