package com.softuni.vandi.model.binding;



import com.softuni.vandi.model.enums.ActivityLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CalorieCalculator {

    private Integer weight;

    private Integer height;

    private Integer age;

    private ActivityLevel activityLevel;



    public int calculate(){

        double bmr = 10 * weight + 6.25 * height - 5 * age + activityLevel.getFactor();
        return (int) Math.round(bmr);

    }

}
