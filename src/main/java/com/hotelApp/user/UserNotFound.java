package com.hotelApp.user;

public class UserNotFound extends RuntimeException {
  public UserNotFound() {
    super("Username not found");
  }
}
