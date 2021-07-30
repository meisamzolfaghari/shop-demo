package com.example.shopdemo;

import com.example.shopdemo.entity.Role;
import com.example.shopdemo.entity.User;
import com.example.shopdemo.repository.UserRepository;
import com.example.shopdemo.utils.ApplicationContextProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class ShopDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopDemoApplication.class, args);

        createAdminUserIfNotExist();
    }


    private static void createAdminUserIfNotExist() {
        UserRepository userRepository = ApplicationContextProvider.getContext().getBean(UserRepository.class);

        if (notExistsAnyAdmin(userRepository)) {

            PasswordEncoder passwordEncoder = ApplicationContextProvider.getContext().getBean(PasswordEncoder.class);

            User adminUser = User.builder()
                    .email("zsmeisam@gmail.com")
                    .passwordHash(passwordEncoder.encode("123"))
                    .firstName("meisam").lastName("zolfaghari")
                    .roles(Set.of(Role.ADMIN, Role.USER)).build();

            userRepository.save(adminUser);
        }
    }

    private static boolean notExistsAnyAdmin(UserRepository userRepository) {
        return userRepository.existsAnyAdmin() == 0;
    }

}
