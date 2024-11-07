package com.example.pokergame_gu_lee.controller;

import com.example.pokergame_gu_lee.dto.LoginRequest;
import com.example.pokergame_gu_lee.entity.User;
import com.example.pokergame_gu_lee.repository.UserRepository;
import com.example.pokergame_gu_lee.util.JwtUtil;
import com.example.pokergame_gu_lee.dto.AuthResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));

        return ResponseEntity.ok()
                .headers(headers)
                .body(users);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));

        return ResponseEntity.status(201)
                .headers(headers)
                .body(savedUser);
    }

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
    
    if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
        String token = jwtUtil.generateToken(loginRequest.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, user.get().getEmail()));
    }
    
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Invalid email or password");
}

}