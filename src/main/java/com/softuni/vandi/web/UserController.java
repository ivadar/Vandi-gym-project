package com.softuni.vandi.web;


import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softuni.vandi.model.binding.UserRegisterBindingModel;
import com.softuni.vandi.model.service.UserServiceModel;
import com.softuni.vandi.services.UserService;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/users")
public class UserController {
    private final ModelMapper modelMapper;

    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {

        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        // TODO save in db
        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    @PreAuthorize("isAnonymous()")
    public String onFailedLogin(
            // TODO (gg): this doesn't seem to work
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY,
                username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/login";
    }

//    @PostMapping("/login")
//    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
//                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
//                               HttpSession httpSession){
//        if (bindingResult.hasErrors()){
//            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
//
//            return "redirect:login";
//        }
//
//        UserServiceModel userServiceModel = userService.findByUsernameAndPassword(userLoginBindingModel.getUsername(),userLoginBindingModel.getPassword());
//        if (userServiceModel==null){
//            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
//            redirectAttributes.addFlashAttribute("isFound", false);
//
//            return "redirect:login";
//        }
//
//
//         userService.loginUser(userServiceModel.getId(), userServiceModel.getUsername());
//        return "redirect:/";
//    }


// TODO (gg): This is not needed. Navigation after logout is handled by SecurityFilterChain config
//    @GetMapping("/logout")
//    public String logout(){
//        return "redirect:/";
//    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }
}
