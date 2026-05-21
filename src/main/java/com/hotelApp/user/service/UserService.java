package com.hotelApp.user.service;

import com.hotelApp.user.exception.InvalidCredentials;
import com.hotelApp.user.exception.UserAlreadyExists;
import com.hotelApp.user.exception.UserNotFound;
import com.hotelApp.user.modal.User;
import com.hotelApp.user.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
  private final UserRepo repo;
  private final BCryptPasswordEncoder encoder;

  public  UserService(UserRepo repo, BCryptPasswordEncoder encoder){
    this.repo = repo;
    this.encoder = encoder;
  }

  public void register(User user) throws UserAlreadyExists {
    if(repo.findByUsername(user.getUsername()) != null) throw new UserAlreadyExists();

    User dbUser = new User(user.getUsername(),encoder.encode(user.getPassword()));
    repo.save(dbUser);
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

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repo.findByUsername(username);
  }
}
