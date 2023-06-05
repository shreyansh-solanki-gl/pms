package com.pms.repository;

import com.pms.entity.Doctor;
import com.pms.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM hospital_patient WHERE patient_id = :patientId AND hospital_id = :hospitalId", nativeQuery = true)
  public void deletePatientById(Long patientId, Long hospitalId);
}
