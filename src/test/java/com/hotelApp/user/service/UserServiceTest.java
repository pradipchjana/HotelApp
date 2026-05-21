package com.hotelApp.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

class UserServiceTest {
  UserService service = new UserService(new HashMap<>());

  @BeforeEach
  void registerUser(){
      service.register("john","doe");
  }

  @Test
  void shouldValidateFalseWhenLoginWithWrongUsername(){
      boolean isLoggedIn = service.login("jane","doe");

      assert (!isLoggedIn);
  }

  @Test
  void shouldValidateFalseWhenLoginWithWrongPassword(){
      boolean isLoggedIn = service.login("john","deer");

      assert (!isLoggedIn);
  }

  @Test
  void shouldValidateTrueWhenLoginWithValidCredentials(){
      boolean isLoggedIn = service.login("john","doe");

      assert (isLoggedIn);
  }
}