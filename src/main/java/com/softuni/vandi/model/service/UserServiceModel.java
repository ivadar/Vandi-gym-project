package com.softuni.vandi.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel {
    private Long id;

    private String firstName;
    private String lastName;

    private String username;

    private String password;

    private String email;
}
