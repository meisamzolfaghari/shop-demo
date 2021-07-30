package com.example.shopdemo.controller.dto.user;

import com.example.shopdemo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFullDTO {

    private Long id;

    private String fullName;

    private String email;

    private Set<Role> roles;

    private boolean blocked;

}
