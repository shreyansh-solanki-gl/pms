package com.pms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthCredentialRequest {

  private String username;

  private String password;

  private String role;

}
