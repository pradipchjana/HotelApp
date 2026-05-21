package com.hotelApp.user.service;

import com.hotelApp.user.modal.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {
  private final HashMap<String, User> users;

  public UserService(HashMap<String,User> initialUserMap){
    this.users = initialUserMap;
  }

  public void register(String username,String password) {
    User user = new User(username, password);

    users.put(user.getUsername(),user);
  }

  public boolean login(String username,String password){
    User user = users.get(username);
    if(user == null) return false;

    return user.getPassword().equals(password);
  }
}
