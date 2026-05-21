package com.hotelApp.user.service;

import com.hotelApp.user.modal.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

class UserServiceTest {
  UserService service = new UserService(new HashMap<>());

  @BeforeEach
  void registerUser(){
      service.register(new User("john","doe"));
  }

  @Test
  void shouldValidateFalseWhenLoginWithWrongUsername(){
      boolean isLoggedIn = service.login(new User("jane","doe"));

      assert (!isLoggedIn);
  }

  @Test
  void shouldValidateFalseWhenLoginWithWrongPassword(){
      boolean isLoggedIn = service.login(new User("john","deer"));

      assert (!isLoggedIn);
  }

  @Test
  void shouldValidateTrueWhenLoginWithValidCredentials(){
      boolean isLoggedIn = service.login(new User("john","doe"));

      assert (isLoggedIn);
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