package com.softuni.vandi.model.entity;

import com.softuni.vandi.model.enums.WorkoutEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
public class Workout extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private WorkoutEnum name;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalTime time;
    @Column(nullable = false)
    private BigDecimal price;
    @OneToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;

    @ManyToMany(mappedBy = "workouts", fetch = FetchType.EAGER)
    private List<User> users;
}
