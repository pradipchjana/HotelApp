package com.hotelApp.user.config;

import com.hotelApp.user.modal.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserConfig {

  @Bean
  public User createUser(){
    return new User();
  }
}
