package com.pms.controller;

import com.pms.entity.Patient;
import com.pms.entity.User;
import com.pms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

  @Autowired
  private PatientService patientService;


  @GetMapping("")
  public ResponseEntity<?> getPatient(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(patientService.findPatient(user));
  }

  @PutMapping("")
  public ResponseEntity<?> updatePatient(@RequestBody Patient patient, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(patientService.save(patient, user));
  }
}
