package com.hotelApp.user.exception;

public class InvalidCredentials extends RuntimeException {
  public InvalidCredentials() {
    super("Invalid credentials");
  }
}
