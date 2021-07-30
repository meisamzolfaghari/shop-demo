package com.example.shopdemo.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class User extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    private String firstName;

    private String lastName;

    @Formula("concat(first_name,' ',last_name)")
    private String fullName;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role")
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Column(nullable = false)
    private boolean blocked = false;

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", email='" + getEmail() +
                ", passwordHash='" + getPasswordHash() +
                ", roles=" + getRoles() +
                '}';
    }

    public String[] getRolesAsStringArray() {
        return roles.stream()
                .map(Role::getRoleString)
                .collect(Collectors.toSet())
                .toArray(String[]::new);
    }

}
