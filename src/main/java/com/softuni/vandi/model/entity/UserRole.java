package com.softuni.vandi.model.entity;

import com.softuni.vandi.model.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Getter
@Setter
public class UserRole extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;
}
