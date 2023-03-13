package com.softuni.vandi.model.enums;

import lombok.Getter;

@Getter
public enum ActivityLevel {
    LIGHTLY_ACTIVE("Lightly active (little or no exercise)", 1.2),
    MODERATELY_ACTIVE("Moderately active (exercise 3-4 times per week)", 1.375),
    VERY_ACTIVE("Very active (exercise 6-7 times per week)", 1.55);

    private final String displayName;
    private final double factor;


    ActivityLevel(String displayName, double factor) {
        this.displayName = displayName;
        this.factor = factor;
    }
}

