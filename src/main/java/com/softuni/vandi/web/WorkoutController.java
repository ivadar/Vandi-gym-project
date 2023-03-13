package com.softuni.vandi.web;


import com.softuni.vandi.model.binding.CalorieCalculator;
import com.softuni.vandi.services.UserService;
import com.softuni.vandi.services.WorkoutService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/workouts")
public class WorkoutController {
    private WorkoutService workoutService;
    private final UserService userService;



    public WorkoutController(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;

    }

//    @GetMapping("/sign-up/{id}")
//    public String signUp(@PathVariable Long id){
//        this.workoutService.signUp(id);
//        return "redirect:/workouts/workout";
//
//    }



   @GetMapping("/workout")
   public String post(Model model){
//        List<SignedUpWorkoutsOfUser> signedUpWorkoutsOfUsers = this.userService.currentWorkouts();
//        model.addAttribute("currentWorkouts", signedUpWorkoutsOfUsers);
       return "/workout";
   }

   // TODO (gg): shouldn't need PreAuthorize if authorization is setup properly in SecurityFilterChain
    @GetMapping("/cancel/{id}")
    @PreAuthorize("isAuthenticated()")
    public String cancel(@PathVariable Long id){
        return "redirect:/workouts/workout";

    }

    @GetMapping("/calculator")
    @PreAuthorize("isAuthenticated()")
    public String calculator(Model model){
        model.addAttribute("calculator", new CalorieCalculator());


        return "calculator";

    }

    @GetMapping("/calculate")
    @PreAuthorize("isAuthenticated()")
    public String calculate(Model model){
        model.addAttribute("calculator",new CalorieCalculator());
        return "calculator";

    }

    @PostMapping("/calculate")
    @PreAuthorize("isAuthenticated()")
    public String calculate(@ModelAttribute("calculator") CalorieCalculator calculator, Model model) {
        int result = calculator.calculate();

        model.addAttribute("result", result);
        return "calculator";
    }
















}
