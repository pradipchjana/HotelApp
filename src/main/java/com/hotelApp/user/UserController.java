package com.hotelApp.user;

import com.hotelApp.user.modal.User;
import com.hotelApp.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService service;

  public UserController(UserService service){
    this.service = service;
  }

  @PostMapping("/register")
  String registerUser(@RequestBody User user){
      service.register(user);
      return "User created successfully.";
  }

  @PostMapping("/login")
  String loginUser(@RequestBody User user){
    boolean canLogIn = service.login(user);

    if(canLogIn) return "Login successful.";

    return "Login failed.";
  }
}
