package com.example.shopdemo.repository;

import com.example.shopdemo.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends AbstractEntityRepository<User> {

    Optional<User> findByEmail(String email);

    @Query(value =
            "select exists(select * from shop_demo.users u " +
                    "join shop_demo.user_roles r on u.id = r.user_id " +
                    "where r.role = 'ADMIN');",
            nativeQuery = true)
    int existsAnyAdmin();
}
