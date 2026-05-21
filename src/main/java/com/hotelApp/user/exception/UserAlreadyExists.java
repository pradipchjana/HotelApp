package com.hotelApp.user.exception;

public class UserAlreadyExists extends RuntimeException {
  public UserAlreadyExists() {
    super("User already exists");
  }
}
