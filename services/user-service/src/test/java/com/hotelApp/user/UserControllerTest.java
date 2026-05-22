package com.hotelApp.user;

import com.hotelApp.user.modal.User;
import com.hotelApp.user.records.LoginRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.Objects;

@SpringBootTest
@AutoConfigureRestTestClient
class UserControllerTest {
  @Autowired
  private RestTestClient client;

  @Test
  void shouldLoginPass(){
    User user = new User("john", "doe");
    LoginRecord responseBody = client.post().uri("/api/users/login").body(user).exchange().expectStatus().isOk().expectBody(LoginRecord.class).returnResult().getResponseBody();

    assert (Objects.requireNonNull(responseBody).message().equals("Login successful"));
  }

//  @Test
//  void shouldLoginFailWithInvalidCredentials(){
//    User user = new User("john", "jane Doe");
//    LoginRecord responseBody = client.post().uri("/api/users/login").body(user).exchange().expectStatus().isOk().expectBody(LoginRecord.class).returnResult().getResponseBody();
//
//    assert (Objects.requireNonNull(responseBody).message().equals("Invalid credentials"));
//  }
//
//  @Test
//  void shouldLoginFailWithNoUserFound(){
//    User user = new User("jane", "Doe");
//    LoginRecord responseBody = client.post().uri("/api/users/login").body(user).exchange().expectStatus().isOk().expectBody(LoginRecord.class).returnResult().getResponseBody();
//
//    assert (Objects.requireNonNull(responseBody).message().equals("User not found"));
//  }
}