package com.example.shopdemo.service;

import com.example.shopdemo.entity.User;
import com.example.shopdemo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, UserRepository> {

    protected UserService(UserRepository repository) {
        super(repository);
    }



}
