package com.softuni.vandi.view;


import com.softuni.vandi.model.entity.Trainer;
import com.softuni.vandi.model.enums.WorkoutEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
public class SignedUpWorkoutsOfUser {

    private Long id;
    private WorkoutEnum name;
    private Trainer trainer;
    private LocalTime time;
    private BigDecimal price;

}
