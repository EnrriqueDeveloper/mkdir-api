package com.tecsup.endpint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tecsup.endpint.model.User;
import com.tecsup.endpint.repository.UserRepository;
import java.util.Optional;

@RestController
@RequestMapping("/api_mkdir/login")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/auth")
    public ResponseEntity<String> login(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        com.tecsup.endpint.service.UserController.LoginResult loginResult = validateCredentials(email, password);

        switch (loginResult) {
            case LOGIN_SUCCESSFUL:
                return ResponseEntity.ok("Login successful");
            case INVALID_CREDENTIALS:
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            case USER_NOT_FOUND:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error");
        }
    }

    public enum LoginResult {
        LOGIN_SUCCESSFUL,
        INVALID_CREDENTIALS,
        USER_NOT_FOUND
    }

    public LoginResult validateCredentials(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (password.equals(user.getPassword())) {
                return com.tecsup.endpint.service.UserController.LoginResult.LOGIN_SUCCESSFUL;
            } else {
                return com.tecsup.endpint.service.UserController.LoginResult.INVALID_CREDENTIALS;
            }
        }
        return com.tecsup.endpint.service.UserController.LoginResult.USER_NOT_FOUND;
    }

}

