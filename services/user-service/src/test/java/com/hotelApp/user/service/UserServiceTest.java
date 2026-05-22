package com.hotelApp.user.service;

import com.hotelApp.user.exception.InvalidCredentials;
import com.hotelApp.user.exception.UserNotFound;
import com.hotelApp.user.modal.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfiguration
class UserServiceTest {

  @Autowired
  UserService service;
  User testUser = new User("john","doe");

  @BeforeEach
  void addUser(){
    service.removeByUsername(testUser.getUsername());
    service.register(testUser);
  }

  @Test
  void shouldValidateFalseWhenLoginWithWrongUsername(){
    assertThrows(UserNotFound.class,() -> service.login(new User("jane","doe")));
  }

  @Test
  void shouldValidateFalseWhenLoginWithWrongPassword(){
     assertThrows(InvalidCredentials.class, ()->service.login(new User("john","deer")));
  }

  @Test
  void shouldReturnTrueIfUserPresent(){
    assert (service.isUserPresent("john"));
  }

  @Test
  void shouldReturnFalseIfUserNotPresent(){
    assert (!service.isUserPresent("jane"));
  }
}