package com.example.shopdemo.config;

import com.example.shopdemo.entity.User;
import com.example.shopdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("Authenticating user with email={}", email);

        User user = userService.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with email=%s was not found", email)));

        return new CurrentUser(user);
    }
}
