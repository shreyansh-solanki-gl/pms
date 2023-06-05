package com.pms.controller;

import com.pms.dto.AuthCredentialRequest;
import com.pms.entity.*;
import com.pms.service.*;
import com.pms.util.CustomPasswordEncoder;
import com.pms.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private CustomPasswordEncoder customPasswordEncoder;

  @Autowired
  private UserDetailServiceImpl userDetailService;

  @Autowired
  private AuthorityService authorityService;

  @Autowired
  private PatientService patientService;

  @Autowired
  private DoctorService doctorService;

  @Autowired
  private HospitalService hospitalService;

  @PostMapping("login")
  public ResponseEntity<?> login(@RequestBody AuthCredentialRequest request) {
    try {
//      Authentication authenticate = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
      Authentication authenticate = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getUsername(), request.getPassword()
              )
      );

      User user = (User) authenticate.getPrincipal();

      return ResponseEntity.ok()
              .header(
                      HttpHeaders.AUTHORIZATION,
                      jwtUtil.generateToken(user)
              )
              .body(user);
    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @GetMapping("/validate")
  public ResponseEntity<?> validateToken(@RequestParam String token, @AuthenticationPrincipal User user) {
    try {
      Boolean isTokenValid = jwtUtil.validateToken(token, user);
      return ResponseEntity.ok(isTokenValid);
    } catch (ExpiredJwtException e) {
      return ResponseEntity.ok(false);
    }

  }

  @PostMapping("register")
  public ResponseEntity<?> register(@RequestBody AuthCredentialRequest request) {
      User user = new User();
      user.setUsername(request.getUsername());
      user.setPassword(customPasswordEncoder.getPasswordEncoder().encode(request.getPassword()));
      user.setRegistrationDate(LocalDate.now());
      userDetailService.save(user);
      User tempUser = userDetailService.findUserByName(request.getUsername());
      Authority authority = new Authority();
      authority.setAuthority(request.getRole());
      authority.setUser(tempUser);
      authorityService.save(authority);

      if(request.getRole().equals("ROLE_PATIENT")){
        Patient patient = new Patient();
        patient.setUser(user);
        patientService.save(patient, user);
      }
      if(request.getRole().equals("ROLE_DOCTOR")){
        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctorService.save(doctor);
      }
      if(request.getRole().equals("ROLE_HOSPITAL")) {
        Hospital hospital = new Hospital();
        hospital.setUser(user);
        hospitalService.save(hospital);
      }

    try {
//      Authentication authenticate = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
      Authentication authenticate = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getUsername(), request.getPassword()
              )
      );

      user = (User) authenticate.getPrincipal();

      return ResponseEntity.ok()
              .header(
                      HttpHeaders.AUTHORIZATION,
                      jwtUtil.generateToken(user)
              )
              .body(user);
    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

}
