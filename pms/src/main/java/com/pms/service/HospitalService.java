package com.pms.service;

import com.pms.entity.*;
import com.pms.repository.AppointmentRepository;
import com.pms.repository.HospitalRepository;
import com.pms.repository.ScheduledAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
  @Autowired
  private HospitalRepository hospitalRepository;

  @Autowired
  private DoctorService doctorService;

  @Autowired
  private PatientService patientService;
  @Autowired
  private AppointmentRepository appointmentRepository;
  @Autowired
  private ScheduledAppointmentRepository scheduledAppointmentRepository;

  public Optional<Hospital> findHospital(User user) {
    return hospitalRepository.findById(user.getHospital().getId());
  }

  public List<Doctor> findDoctors(User user) {
    return doctorService.findDoctorByHospital(user);
  }

  public List<Optional<Patient>> findPatients(User user) {
    return patientService.findPatientByHospital(user);
  }

  public Optional<Doctor> findDoctorById(Long doctorId) {
    return doctorService.findDoctorById(doctorId);
  }

  public Optional<Patient> findPatientById(Long patientId) {
    return patientService.findPatientById(patientId);
  }

  public boolean deletePatientById(Long patientId, User user) {
    hospitalRepository.deletePatientById(patientId, user.getHospital().getId());
    return true;
  }

  public List<Doctor> removeDoctorById(Long doctorId, User user) {
    return doctorService.removeHospitalById(doctorId, user.getHospital().getId());
  }

  public void selectDoctor(Long doctorId, User user) {
    doctorService.saveHospitalId(doctorId, user.getHospital().getId());
  }

  public List<List<ScheduledAppointment>> findOngoingScheduledAppointments(User user) {
    List<Doctor> doctors = user.getHospital().getDoctor();
    List<Long> appointmentIds;
    List<ScheduledAppointment> scheduledAppointments = new ArrayList<>();
    List<List<ScheduledAppointment>> newScheduledAppointments = new ArrayList<>();
    for (Doctor doctor : doctors) {
      appointmentIds = appointmentRepository.findAppointmentIdByDoctorId(doctor.getId());
      for (Long appointmentId : appointmentIds) {
        ScheduledAppointment tempAppointment = scheduledAppointmentRepository.findActiveScheduledAppointmentByAppointmentId(appointmentId);
        if(tempAppointment != null)
          scheduledAppointments.add(tempAppointment);
      }
      newScheduledAppointments.add(scheduledAppointments);
    }
    return newScheduledAppointments;
  }

  public List<List<ScheduledAppointment>> findClosedScheduledAppointments(User user) {
    List<Doctor> doctors = user.getHospital().getDoctor();
    List<Long> appointmentIds;
    List<ScheduledAppointment> scheduledAppointments = new ArrayList<>();
    List<List<ScheduledAppointment>> newScheduledAppointments = new ArrayList<>();
    for (Doctor doctor : doctors) {
      appointmentIds = appointmentRepository.findAppointmentIdByDoctorId(doctor.getId());
      for (Long appointmentId : appointmentIds) {
        ScheduledAppointment tempAppointment = scheduledAppointmentRepository.findClosedScheduledAppointmentByAppointmentId(appointmentId);
        if(tempAppointment != null)
          scheduledAppointments.add(tempAppointment);
      }
      newScheduledAppointments.add(scheduledAppointments);
    }

    return newScheduledAppointments;
  }

  public List<Appointment> findOngoingAppointments(User user) {
    List<Doctor> doctors = user.getHospital().getDoctor();
    List<Appointment> appointments = new ArrayList<>();

    for (Doctor doctor : doctors) {
      appointments = appointmentRepository.findByStatusAndDoctorId("on-going", doctor.getId());
    }
    return appointments;
  }

  public List<Appointment> findClosedAppointments(User user) {
    List<Doctor> doctors = user.getHospital().getDoctor();
    List<Appointment> appointments = new ArrayList<>();

    for (Doctor doctor : doctors) {
      appointments = appointmentRepository.findByStatusAndDoctorId("closed", doctor.getId());
    }
    return appointments;
  }

  public List<Doctor> findDoctorBySpeciality(String speciality, User user) {
    List<Doctor> doctors = user.getHospital().getDoctor();

    List<Doctor> doctorsWithSpeciality = new ArrayList<>();
    for (Doctor doctor : doctors) {
      doctorsWithSpeciality.add(doctorService.findDoctorBySpecialityAndDoctorId(speciality, doctor.getId()));
    }
    return doctorsWithSpeciality;
  }

  public List<Doctor> findAllDoctors() {
    return doctorService.findAllDoctors();
  }

  public Hospital save(Hospital hospital) {
    return hospitalRepository.save(hospital);
  }
}
