package com.hotelApp.user.service;

import com.hotelApp.user.exception.InvalidCredentials;
import com.hotelApp.user.exception.UserAlreadyExists;
import com.hotelApp.user.exception.UserNotFound;
import com.hotelApp.user.modal.User;
import com.hotelApp.user.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepo repo;

  public  UserService(UserRepo repo){
    this.repo = repo;
  }

  public void register(User user) throws UserAlreadyExists {
    if(repo.findByUsername(user.getUsername()) != null) throw new UserAlreadyExists();
    repo.save(user);
  }

  public String login(User user) throws RuntimeException {
    User dbUser;
    if(user == null ||(dbUser= repo.findByUsername(user.getUsername())) == null) throw new UserNotFound();

    if(!user.getPassword().equals(dbUser.getPassword())) throw new InvalidCredentials();

    return dbUser.getId();
  }

  public boolean isUserPresent(String username){
    return  repo.findByUsername(username) != null;
  }

  public void removeByUsername(String username) {
    repo.removeAllByUsername(username);
  }

  public void removeAllUsers() {
    repo.deleteAll();
  }
}
