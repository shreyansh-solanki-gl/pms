package com.pms.controller;

import com.pms.entity.ScheduledAppointment;
import com.pms.entity.User;
import com.pms.service.ScheduledAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments/scheduledAppointments")
public class ScheduledAppointmentController {

  @Autowired
  private ScheduledAppointmentService scheduledAppointmentService;

//  @GetMapping("")
//  public ResponseEntity<?> getScheduledAppointment(@AuthenticationPrincipal User user) {
//    List<ScheduledAppointment> scheduledAppointmentsByAppointment = scheduledAppointmentService.findByAppointment(user);
//    return ResponseEntity.ok(scheduledAppointmentsByAppointment);
//  }

  @PostMapping("")
  public ResponseEntity<?> createScheduledAppointment(@RequestBody ScheduledAppointment scheduledAppointment, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(scheduledAppointmentService.save(scheduledAppointment));
  }

  @PutMapping("")
  public ResponseEntity<?> updateScheduledAppointment(@RequestBody ScheduledAppointment scheduledAppointment, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(scheduledAppointmentService.update(scheduledAppointment));
  }

  @GetMapping("/closed/{appointmentId}")
  public ResponseEntity<?> getClosedAppointment(@PathVariable Long appointmentId, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(scheduledAppointmentService.findByAppointmentStatus(appointmentId,"closed",user));
  }

  @GetMapping("/active/{appointmentId}")
  public ResponseEntity<?> getPendingAppointment(@PathVariable Long appointmentId, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(scheduledAppointmentService.findByAppointmentStatus(appointmentId, "active",user));
  }
}
