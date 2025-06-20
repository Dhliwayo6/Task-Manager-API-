package com.example.taskmanager.controller;

import com.example.taskmanager.model.UpdateUserCommand;
import com.example.taskmanager.model.User;
import com.example.taskmanager.model.UserDTO;
import com.example.taskmanager.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final CreateUserService createUserService;
    private final GetUserService getUserService;
    private final GetUsersService getUsersService;
    private final DeleteUserService deleteUserService;
    private final UpdateUserService updateUserService;


    public UserController(CreateUserService createUserService,
                          GetUserService getUserService,
                          GetUsersService getUsersService,
                          DeleteUserService deleteUserService,
                          UpdateUserService updateUserService) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
        this.getUsersService = getUsersService;
        this.deleteUserService = deleteUserService;
        this.updateUserService = updateUserService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user){
        return createUserService.execute(user);
    }

    @GetMapping("users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return getUsersService.execute(null);
    }

    //new get mapping to find by id

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        return getUserService.execute(id);
    }

    //Search functionality request

//    @GetMapping("/user/search")
//    public ResponseEntity<List<UserDTO>> searchUserByName(@RequestParam String name) {
//        return searchUserService.execute(name);
//    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody User user){
        return updateUserService.execute(new UpdateUserCommand(id, user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        return deleteUserService.execute(id);
    }

}

