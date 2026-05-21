package com.hotelApp.user.exception;

public class UserNotFound extends RuntimeException {
  public UserNotFound() {
    super("User not found");
  }
}
