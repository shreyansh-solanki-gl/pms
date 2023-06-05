package com.pms.controller;

import com.pms.entity.Hospital;
import com.pms.entity.User;
import com.pms.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

  @Autowired
  private HospitalService hospitalService;

  @GetMapping("")
  public ResponseEntity<?> getHospital(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.findHospital(user));
  }

  @PutMapping("")
  public ResponseEntity<?> updateHospital(@RequestBody Hospital hospital, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.save(hospital));
  }

  @GetMapping("/doctors")
  public ResponseEntity<?> getDoctors(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.findDoctors(user));
  }

  @GetMapping("/patients")
  public ResponseEntity<?> getPatients(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.findPatients(user));
  }

  @GetMapping("/doctors/all")
  public ResponseEntity<?> getAllDoctors(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.findAllDoctors());
  }

  @GetMapping("/doctors/{doctorId}")
  public ResponseEntity<?> getDoctor(@PathVariable Long doctorId, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.findDoctorById(doctorId));
  }

  @GetMapping("/patients/{patientId}")
  public ResponseEntity<?> getPatient(@PathVariable Long patientId, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.findPatientById(patientId));
  }

  @PutMapping("/doctors/{doctorId}")
  public ResponseEntity<?> removeDoctor(@PathVariable Long doctorId, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.removeDoctorById(doctorId, user));
  }

  @DeleteMapping("/patients/{patientId}")
  public ResponseEntity<?> deletePatient(@PathVariable Long patientId, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.deletePatientById(patientId,user));
  }

  @PutMapping("/doctors/selectDoctor/{doctorId}")
  public ResponseEntity<?> selectDoctor(@PathVariable Long doctorId, @AuthenticationPrincipal User user) {
    hospitalService.selectDoctor(doctorId, user);
    return ResponseEntity.ok(hospitalService.findDoctorById(doctorId));
  }

  @GetMapping("/doctors/appointments/ongoing")
  public ResponseEntity<?> getOngoingAppointment(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.findOngoingAppointments(user));
  }

  @GetMapping("/doctors/appointments/closed")
  public ResponseEntity<?> getClosedAppointment(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.findClosedAppointments(user));
  }

  @GetMapping("/doctors/scheduledAppointments/ongoing")
  public ResponseEntity<?> getOngoingScheduledAppointment(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.findOngoingScheduledAppointments(user));
  }

  @GetMapping("/doctors/scheduledAppointments/closed")
  public ResponseEntity<?> getClosedScheduledAppointment(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.findClosedScheduledAppointments(user));
  }

  @GetMapping("/doctors/speciality/{speciality}")
  public ResponseEntity<?> getDoctorsBySpeciality(@PathVariable String speciality, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(hospitalService.findDoctorBySpeciality(speciality, user));
  }

}
