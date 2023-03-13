package com.softuni.vandi.web;



import com.softuni.vandi.view.WorkoutsScheduleViewModel;
import com.softuni.vandi.services.WorkoutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final WorkoutService workoutService;

    public HomeController(WorkoutService workoutService) {

        this.workoutService = workoutService;
    }
    @GetMapping("/")
    public String index(Model model, Principal principal){
        if (principal==null){
            return "index";
        } else{
            List<WorkoutsScheduleViewModel> scheduele = this.workoutService.findAll();
            model.addAttribute("scheduele",scheduele);

            System.out.println(scheduele.size());

            return "home";
        }

    }

    @GetMapping("/about")
    public String showAboutUsPage() {
        return "about";
    }

}
