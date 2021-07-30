package com.example.shopdemo.controller.api;

import com.example.shopdemo.controller.dto.user.UserFullDTO;
import com.example.shopdemo.controller.dto.user.UserLightDTO;
import com.example.shopdemo.entity.User;
import com.example.shopdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final ModelMapper modelMapper;

    private final UserService userService;

    @GetMapping("block/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserFullDTO blockUser(@PathVariable("id") Long id) {

        User user = getUserById(id);

        userService.blockUser(user);

        return modelMapper.map(user, UserFullDTO.class);
    }

    @GetMapping("unblock/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserFullDTO unblockUser(@PathVariable("id") Long id) {

        User user = getUserById(id);

        userService.unblockUser(user);

        return modelMapper.map(user, UserFullDTO.class);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserLightDTO> getAllUsers(@RequestParam(defaultValue = "0", required = false) int start,
                                          @RequestParam(defaultValue = "10", required = false) int size) {
        return userService.findAll(PageRequest.of(start, size))
                .map(user -> modelMapper.map(user, UserLightDTO.class));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserFullDTO getUser(@PathVariable("id") Long id) {

        User user = getUserById(id);

        return modelMapper.map(user, UserFullDTO.class);
    }

    private User getUserById(Long id) {
        return userService.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("user with id: %s was not found!", id)));
    }

}
