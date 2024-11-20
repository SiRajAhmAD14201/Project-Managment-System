package com.bluebarry.project_managment_sytem.controller;

import com.bluebarry.project_managment_sytem.dto.request.UserRequest;
import com.bluebarry.project_managment_sytem.dto.response.UserResponse;
import com.bluebarry.project_managment_sytem.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUser(){
    List<UserResponse> userResponses=userService.getAllUser();
    return new ResponseEntity<>(userResponses,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        UserResponse userResponse=userService.getUserById(id);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,@RequestBody UserRequest userRequest){
        UserResponse userRequest1=userService.updateUser(id, userRequest);
        return new ResponseEntity<>(userRequest1,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest){
        UserResponse userRequest1=userService.saveUser(userRequest);
        return  new ResponseEntity<>(userRequest1, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
