package com.pms.service;

import com.pms.entity.User;
import com.pms.repository.UserRepository;
import com.pms.util.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private CustomPasswordEncoder customPasswordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);
    return user.orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
  }

  public Optional<User> find(User user) {
    return userRepository.findById(user.getId());
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public User findUserByName(String username) {
    return userRepository.findByName(username);
  }
}
