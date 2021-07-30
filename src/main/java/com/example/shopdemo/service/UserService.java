package com.example.shopdemo.service;

import com.example.shopdemo.entity.User;
import com.example.shopdemo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends AbstractService<User, UserRepository> {

    protected UserService(UserRepository repository) {
        super(repository);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }


}
