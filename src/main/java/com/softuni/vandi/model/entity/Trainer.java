package com.softuni.vandi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "trainers")
@Getter
@Setter
@NoArgsConstructor

public class Trainer extends BaseEntity{
    @Column
    private String firstName;
    @Column
    private String lastName;
    @OneToOne()
    @JoinColumn(name = "workout_id", referencedColumnName = "id")
    private Workout workout;

    @ManyToMany
    @JoinTable(
            name = "trainers_users",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

}
