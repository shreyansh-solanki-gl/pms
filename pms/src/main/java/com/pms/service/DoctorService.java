package com.pms.service;

import com.pms.entity.*;
import com.pms.repository.AppointmentRepository;
import com.pms.repository.DoctorRepository;
import com.pms.repository.ScheduledAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  private DoctorWorkingDayService doctorWorkingDayService;

  @Autowired
  private ScheduledAppointmentRepository scheduledAppointmentRepository;

  @Autowired
  private AppointmentRepository appointmentRepository;

  public Doctor save(Doctor doctor) {
    System.out.println(doctor);
    return doctorRepository.save(doctor);
  }

  public List<Doctor> getAllDoctors() {
    return doctorRepository.findAll();
  }

  public Optional<Doctor> findDoctor(User user) {
    return doctorRepository.findById(user.getDoctor().getId());
  }

  public List<Appointment> findAppointments(User user){
    return appointmentRepository.findByDoctor(user.getDoctor());
  }

  public List<Appointment> findByStatusAndDoctorId(String status, User user) {
    return appointmentRepository.findByStatusAndDoctorId(status, user.getDoctor().getId());
  }

  public DoctorWorkingDay saveScheduleTime(DoctorWorkingDay doctorWorkingDay, User user) {
    return doctorWorkingDayService.save(doctorWorkingDay, user);
  }

  public List<DoctorWorkingDay> getScheduleTime(User user) {
    return doctorWorkingDayService.findByDoctor(user.getDoctor());
  }

  public List<Doctor> findDoctorByHospital(User user) {
    return doctorRepository.findDoctorByHospitalId(user.getHospital().getId());

  }

  public Optional<Doctor> findDoctorById(Long doctorId) {
    return doctorRepository.findById(doctorId);
  }


  public boolean deleteDoctorById(Long doctorId) {
    doctorRepository.deleteById(doctorId);
    return true;
  }

  public List<Doctor> removeHospitalById(Long doctorId, Long hospitalId) {
    doctorRepository.removeHospitalById(doctorId, hospitalId);
    return doctorRepository.findDoctorByHospitalId(hospitalId);
  }

  public void saveHospitalId(Long doctorId, Long hospitalId) {
    doctorRepository.saveHospitalId(doctorId, hospitalId);
  }

  public Doctor findDoctorBySpecialityAndDoctorId(String speciality, Long doctorId) {
    return doctorRepository.findDoctorBySpecialityAndDoctorId(speciality, doctorId);
  }

  public List<Doctor> findAllDoctors() {
    return doctorRepository.findAll();
  }
}
