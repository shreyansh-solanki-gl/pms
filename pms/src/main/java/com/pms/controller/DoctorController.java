package com.pms.controller;

import com.pms.entity.Doctor;
import com.pms.entity.DoctorWorkingDay;
import com.pms.entity.User;
import com.pms.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

  @Autowired
  private DoctorService doctorService;

  @GetMapping("/all")
  public ResponseEntity<?> getAllDoctors() {
    return ResponseEntity.ok(doctorService.getAllDoctors());
  }

  @GetMapping("")
  public ResponseEntity<?> getDoctor(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(doctorService.findDoctor(user));
  }

  @PutMapping("")
  public ResponseEntity<?> setDoctor(@RequestBody Doctor doctor, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(doctorService.save(doctor));
  }

  @GetMapping("/appointments")
  public ResponseEntity<?> getAppointments(@AuthenticationPrincipal User user){
    return ResponseEntity.ok(doctorService.findAppointments(user));
  }

  @GetMapping("/appointments/ongoing")
  public ResponseEntity<?> getOngoingAppointment(@AuthenticationPrincipal User user){
    return ResponseEntity.ok(doctorService.findByStatusAndDoctorId("on-going", user));
  }

  @GetMapping("/appointments/closed")
  public ResponseEntity<?> getClosedAppointment(@AuthenticationPrincipal User user){
    return ResponseEntity.ok(doctorService.findByStatusAndDoctorId("closed", user));
  }

  @GetMapping("/scheduleTime")
  public ResponseEntity<?> setScheduleTime(@AuthenticationPrincipal User user){
    return ResponseEntity.ok(doctorService.getScheduleTime(user));
  }

  @PutMapping("/scheduleTime")
  public ResponseEntity<?> setScheduleTime(@RequestBody DoctorWorkingDay doctorWorkingDay,  @AuthenticationPrincipal User user){
    return ResponseEntity.ok(doctorService.saveScheduleTime(doctorWorkingDay, user));
  }

}
