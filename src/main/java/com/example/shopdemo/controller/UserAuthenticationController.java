package com.example.shopdemo.controller;

import com.example.shopdemo.controller.dto.user.UserProfileDTO;
import com.example.shopdemo.controller.dto.user.UserRegisterDTO;
import com.example.shopdemo.config.CurrentUser;
import com.example.shopdemo.entity.Role;
import com.example.shopdemo.entity.User;
import com.example.shopdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserAuthenticationController {

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @GetMapping("current-user")
    public UserProfileDTO getAllProducts(final Authentication authentication) {

        User user = authentication == null ? null : ((CurrentUser) authentication.getPrincipal()).getUser();

        return user == null ? null : modelMapper.map(user, UserProfileDTO.class);
    }

    @PutMapping("register")
    @ResponseStatus(HttpStatus.OK)
    public void registerUser(@Validated @RequestBody UserRegisterDTO userRegisterDTO) {

        validateEmailString(userRegisterDTO.getEmail());
        validateUserNotExistByUserName(userRegisterDTO.getEmail());

        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setPasswordHash(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setRoles(Set.of(Role.USER));

        userService.save(user);
    }

    private void validateEmailString(String email) {
        if (!EmailValidator.getInstance().isValid(email))
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    String.format("email: %s does not have a valid pattern!", email));
    }

    private void validateUserNotExistByUserName(String email) {
        if (userService.findByEmail(email).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    String.format("user with username: %s already exists!", email));
    }

}
