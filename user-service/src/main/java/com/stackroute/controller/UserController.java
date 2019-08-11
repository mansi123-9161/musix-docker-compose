package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        User savedUser=userService.saveUser(user);
        return new ResponseEntity<>(savedUser,HttpStatus.OK);
    }
    @GetMapping("user/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable int id){
        User userDetails=userService.getUserById(id);
        return new ResponseEntity<>(userDetails,HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity<?> getAllUsers()
    {
        return new ResponseEntity<List<User>>( userService.getAllUsers(), HttpStatus.OK);
    }

}
