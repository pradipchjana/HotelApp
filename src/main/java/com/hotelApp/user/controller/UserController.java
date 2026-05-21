package com.hotelApp.user.controller;

import com.hotelApp.user.modal.User;
import com.hotelApp.user.records.LoginRecord;
import com.hotelApp.user.records.SignUpRecord;
import com.hotelApp.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService service;
  public UserController(UserService service){
    this.service = service;
  }

  @PostMapping("/register")
  SignUpRecord registerUser(@RequestBody User user){
    try {
      service.register(user);
      return new SignUpRecord("User created successfully");
    } catch (Exception e) {
      return  new SignUpRecord(e.getMessage());
    }
  }

  @PostMapping("/login")
  LoginRecord loginUser(@RequestBody User user){
    try {
      System.out.println("username: "+user.getUsername() + ", password:  "+user.getPassword()+", id: "+user.getId());
      String token =  service.login(user);
      return new LoginRecord(token, "Login successful");
    }catch (Exception e){
      return new LoginRecord(null,e.getMessage());
    }
  }

  @PatchMapping("/remove-all-users")
  void removeUsers(){
    service.removeAllUsers();
  }
}
