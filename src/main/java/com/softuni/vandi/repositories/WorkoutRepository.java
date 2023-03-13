package com.softuni.vandi.repositories;


import com.softuni.vandi.model.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    Optional<Workout> findByTrainer_Id(Long id);
    List<Workout> findAll();



}
