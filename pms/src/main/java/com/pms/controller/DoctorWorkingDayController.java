package com.pms.controller;

import com.pms.entity.DoctorWorkingDay;
import com.pms.entity.User;
import com.pms.service.DoctorWorkingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctorWorkingDay")
public class DoctorWorkingDayController {

  @Autowired
  DoctorWorkingDayService doctorWorkingDayService;

  @GetMapping("{doctorId}")
  public ResponseEntity<?> getDoctorWorkingDay(@PathVariable Long doctorId, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(doctorWorkingDayService.getAll(doctorId));
  }

  @PostMapping("")
  public ResponseEntity<?> createWorkingDay(@RequestBody DoctorWorkingDay doctorWorkingDay, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(doctorWorkingDayService.save(doctorWorkingDay, user));
  }


}
