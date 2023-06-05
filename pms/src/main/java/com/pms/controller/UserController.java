package com.pms.controller;

import com.pms.entity.User;
import com.pms.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  UserDetailServiceImpl userDetailService;

  @GetMapping("")
  public ResponseEntity<Optional<User>> getUser(@AuthenticationPrincipal User user){
    return ResponseEntity.ok(userDetailService.find(user));
  }
}
