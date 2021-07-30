package com.example.shopdemo.service;

import com.example.shopdemo.entity.User;
import com.example.shopdemo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService extends AbstractService<User, UserRepository> {

    protected UserService(UserRepository repository) {
        super(repository);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }


    @Transactional
    public void blockUser(User user) {

        if (!user.isBlocked()) {
            user.setBlocked(true);
            save(user);
        }
    }

    @Transactional
    public void unblockUser(User user) {

        if (user.isBlocked()) {
            user.setBlocked(false);
            save(user);
        }
    }

}
