package com.tecsup.endpint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tecsup.endpint.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<com.tecsup.endpint.model.User> findByEmail(String email);
}
