package com.pms.service;

import com.pms.entity.*;
import com.pms.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppointmentService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  private DoctorService doctorService;

  @Autowired
  private PatientService patientService;


  public Appointment save(User user) {
    Appointment appointment = new Appointment();
    appointment.setStatus("On-going");
    appointment.setPatient(user.getPatient());
    return appointmentRepository.save(appointment);
  }

  public Appointment save(Appointment appointment, User user) {
//    Appointment appointment = new Appointment();
//    appointment.setDoctor(updatingAppointment.getDoctor());
//    appointment.setModeOfAppointment(updatingAppointment.getModeOfAppointment());
    List<Appointment> patientAppointments = appointmentRepository.findByPatient(user.getPatient());

    for(int i = 0; i < patientAppointments.size(); i++) {
      System.out.println("chla bi ya nahi");
      System.out.println(Objects.equals(patientAppointments.get(i).getDoctor().getId(), appointment.getDoctor().getId()));
      if(patientAppointments.get(i).getStatus().equals("On-going") && (Objects.equals(patientAppointments.get(i).getDoctor().getId(), appointment.getDoctor().getId()))){
        return null;
      }
    }

    appointment.setStatus("On-going");
    appointment.setPatient(user.getPatient());
    Optional<Doctor> doctor = doctorService.findDoctorById(appointment.getDoctor().getId());
    appointmentRepository.savePatientHospital(doctor.get().getHospital().getId(), user.getPatient().getId());
    if(appointment.getStartDateAndTime() == null) appointment.setStartDateAndTime(new Date());

    return appointmentRepository.save(appointment);
  }

  public Optional<Appointment> findById(Long id) {
    return appointmentRepository.findById(id);
  }

  public List<Appointment> findByPatient(User user) {
    return appointmentRepository.findByPatient(user.getPatient());
  }

  public List<Appointment> findByAppointmentStatus(String status, User user) {
    return appointmentRepository.findByStatusAndPatient(status, user.getPatient().getId());
  }

  public List<Appointment> update(Appointment appointment, User user) {
    if(user.getDoctor() != null){
      appointment.setStatus("closed");
      appointment.setEndDateAndTime(new Date());
    }
    appointmentRepository.save(appointment);
    return appointmentRepository.findByStatusAndDoctorId("on-going", user.getDoctor().getId());
  }

//  public List<Appointment> findByStatusAndDoctor(String status, User user) {
//    System.out.println(user.getDoctor().getId());
//    return appointmentRepository.findByStatusAndDoctorId(status, user.getDoctor().getId());
//  }

}
