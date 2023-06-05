package com.pms.service;

import com.pms.entity.Authority;
import com.pms.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {
  @Autowired
  private AuthorityRepository authorityRepository;
  public Authority save(Authority authority) {
    return authorityRepository.save(authority);
  }
}
