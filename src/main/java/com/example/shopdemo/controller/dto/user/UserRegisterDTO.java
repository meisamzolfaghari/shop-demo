package com.example.shopdemo.controller.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

    @NotBlank(message = "email is needed to be filled!")
    private String email;

    @NotBlank(message = "firstName is needed to be filled!")
    private String firstName;

    @NotBlank(message = "lastName is needed to be filled!")
    private String lastName;

    @NotBlank(message = "password is needed to be filled!")
    private String password;

}
