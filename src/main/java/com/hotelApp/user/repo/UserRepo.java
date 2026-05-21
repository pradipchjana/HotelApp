package com.hotelApp.user.repo;

import com.hotelApp.user.modal.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
  User findByUsername(String username);

  void removeAllByUsername(String username);
}
