package com.pms.repository;

import com.pms.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

  @Query(value = "SELECT * FROM hospital_patient WHERE hospital_id =:id", nativeQuery = true)
  List<Long> findPatientByHospitalId(Long id);
}
