package com.softuni.vandi.model.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBindingModel {
    @Size(min=1, max=20)
    @NotBlank(message = "Please, fill your first name!")
    private String firstName;
    @NotBlank(message = "Please, fill your last name")
    @Size(min=1, max=20)
    private String lastName;
   @Email
   @NotNull(message = "Email cannot be empty")
    private String email;
    @NotBlank(message = "You should choose an username")
    @Size(min=3, message = "Username should be more than 3 symbols")
    private String username;
    @NotBlank(message = "You better have a password")
    @Size(min=3, max = 30, message = "Size should be between 3 and 30")
    private String password;
    @NotNull
    @Size(min=3, max = 30)
    private String confirmPassword;
}
