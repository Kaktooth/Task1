package com.test.project.controller;

import com.test.project.entity.UserDto;
import com.test.project.service.UserService;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserRestController {

  private final UserService userService;

  public UserRestController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public UserDto getUser(@PathVariable("id") UUID id) {
    return userService.findUserById(id);
  }
}
