package com.pms.repository;

import com.pms.entity.Appointment;
import com.pms.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {


  @Query(value = "SELECT * FROM doctor WHERE hospital_id = :id", nativeQuery = true)
  List<Doctor> findDoctorByHospitalId(Long id);

  @Modifying
  @Transactional
  @Query(value = "UPDATE doctor SET hospital_id = NULL WHERE id =:doctorId AND hospital_id =:hospitalId", nativeQuery = true)
  void removeHospitalById(Long doctorId, Long hospitalId);

  @Modifying
  @Transactional
  @Query(value = "UPDATE doctor SET hospital_id = :hospitalId WHERE id =:doctorId", nativeQuery = true)
  void saveHospitalId(Long doctorId, Long hospitalId);

  @Query(value = "SELECT * FROM doctor WHERE speciality = :speciality AND id = :doctorId", nativeQuery = true)
  Doctor findDoctorBySpecialityAndDoctorId(String speciality, Long doctorId);
}
