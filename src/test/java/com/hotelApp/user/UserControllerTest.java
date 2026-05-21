package com.hotelApp.user;

import com.hotelApp.user.modal.User;
import org.junit.jupiter.api.BeforeEach;
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

  @BeforeEach
  void registerUser(){
    User user = new User("john", "doe");
    client.post().uri("/api/users/register").body(user).exchange().expectStatus().isOk();
  }

  @Test
  void shouldCreateUser(){
    User user = new User("john", "deer");
    String responseBody = client.post().uri("/api/users/register").body(user).exchange().expectStatus().isOk().expectBody(String.class).returnResult().getResponseBody();

    assert (Objects.requireNonNull(responseBody).equals("User created successfully."));
  }

  @Test
  void shouldLoginPass(){
    User user = new User("john", "doe");
    String responseBody = client.post().uri("/api/users/login").body(user).exchange().expectStatus().isOk().expectBody(String.class).returnResult().getResponseBody();

    assert (Objects.requireNonNull(responseBody).equals("Login successful."));
  }

  @Test
  void shouldLoginFail(){
    User user = new User("john", "jane Doe");
    String responseBody = client.post().uri("/api/users/login").body(user).exchange().expectStatus().isOk().expectBody(String.class).returnResult().getResponseBody();

    assert (Objects.requireNonNull(responseBody).equals("Login failed."));
  }
}