package com.pms.service;

import com.pms.entity.Patient;
import com.pms.entity.User;
import com.pms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

  @Autowired
  private PatientRepository patientRepository;

  public Optional<Patient> findPatient(User user) {
    return patientRepository.findById(user.getPatient().getId());
  }

  public Patient save(Patient patient, User user) {
    return patientRepository.save(patient);
  }



  public List<Optional<Patient>> findPatientByHospital(User user) {
    List<Long> patientIds = patientRepository.findPatientByHospitalId(user.getHospital().getId());
    List<Optional<Patient>> patients = new ArrayList<>();
    for(int i = 0; i < patientIds.size(); i++) {
      patients.add(patientRepository.findById(patientIds.get(i)));

    }
    return patients;
  }

  public Optional<Patient> findPatientById(Long patientId) {
    return patientRepository.findById(patientId);
  }
}
