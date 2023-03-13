package com.softuni.vandi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRole> roles = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_workouts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workout_id"))
    private List<Workout> workouts;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Trainer> trainers;
}
