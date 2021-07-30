package com.example.shopdemo.controller.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLightDTO {

    private Long id;

    private String fullName;

    private String email;

}
