package com.softuni.vandi.init;

import com.softuni.vandi.model.entity.Trainer;
import com.softuni.vandi.repositories.TrainerRepository;
import com.softuni.vandi.repositories.WorkoutRepository;
import com.softuni.vandi.services.TrainerService;
import com.softuni.vandi.services.WorkoutService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseInit implements CommandLineRunner {
    private final WorkoutService workoutService;
    private final WorkoutRepository workoutRepository;
    private final TrainerRepository trainerRepository;
    private final TrainerService trainerService;

    public DatabaseInit(WorkoutService workoutService, WorkoutRepository workoutRepository, TrainerRepository trainerRepository, TrainerService trainerService) {
        this.workoutService = workoutService;
        this.workoutRepository = workoutRepository;
        this.trainerRepository = trainerRepository;
        this.trainerService = trainerService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.trainerService.init();
        this.workoutService.init();

        List<Trainer> trainers = trainerRepository.findAll();
        for (Trainer trainer : trainers) {
            if (trainer.getWorkout()==null){
                trainer.setWorkout(this.workoutRepository.findByTrainer_Id(trainer.getId()).get());
                trainerRepository.saveAndFlush(trainer);
            }
        }


    }
}
