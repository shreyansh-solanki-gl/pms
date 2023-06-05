package com.pms.controller;

import com.pms.entity.Appointment;
import com.pms.entity.User;
import com.pms.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

  @Autowired
  private AppointmentService appointmentService;

  @PostMapping("")
  public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment, @AuthenticationPrincipal User user) {
    if(appointmentService.save(appointment, user) != null){
      return ResponseEntity.ok(appointmentService.save(appointment, user));
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @GetMapping("")
  public ResponseEntity<?> getAppointment(@AuthenticationPrincipal User user) {
    List<Appointment> appointmentByPatient = appointmentService.findByPatient(user);
    return ResponseEntity.ok(appointmentByPatient);
  }

  @GetMapping("{appointmentId}")
  public ResponseEntity<?> getAppointment(@PathVariable Long appointmentId, @AuthenticationPrincipal User user) {
    Optional<Appointment> appointment = appointmentService.findById(appointmentId);
    return ResponseEntity.ok(appointment.orElse(new Appointment()));
  }

  @PutMapping("/{appointmentId}")
  public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(appointmentService.update(appointment, user));
  }

  @GetMapping("/ongoing")
  public ResponseEntity<?> getOngoingAppointment(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(appointmentService.findByAppointmentStatus("on-going", user));
  }

  @GetMapping("/closed")
  public ResponseEntity<?> getClosedAppointment(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(appointmentService.findByAppointmentStatus("closed", user));
  }
}
