package com.test.project.service;

import com.test.project.entity.User;
import com.test.project.entity.UserDto;
import com.test.project.repository.UserRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDto findUserById(UUID id) {
    User user = userRepository.findById(id).get();
    return user.getDto();
  }
}
