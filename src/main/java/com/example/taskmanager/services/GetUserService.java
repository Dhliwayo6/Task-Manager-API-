package com.example.taskmanager.services;

import com.example.taskmanager.Query;
import com.example.taskmanager.model.User;
import com.example.taskmanager.model.UserDTO;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserService implements Query<Integer, UserDTO> {

    private UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<UserDTO> execute(Integer input) {

        Optional<User> userOptional = userRepository.findById(input);

        if (userOptional.isPresent()){
            return ResponseEntity.ok(new UserDTO(userOptional.get()));
        }

        // throw a custom exception in the future
        throw new IllegalArgumentException("User not found");

    }
}
