package com.hotelApp.user.service;

import com.hotelApp.user.UserNotFound;
import com.hotelApp.user.modal.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {
  private final HashMap<String, User> users;

  public UserService(HashMap<String,User> initialUserMap){
    this.users = initialUserMap;
  }

  public void register(User user) {
    users.put(user.getUsername(),user);
  }

  public boolean login(User user) throws UserNotFound {
    if(user == null || users.get(user.getUsername()) == null) throw new UserNotFound();

    String dbPass = users.get(user.getUsername()).getPassword();
    return user.getPassword().equals(dbPass);
  }

  public boolean isUserPresent(String username){
    return  users.get(username) != null;
  }
}
