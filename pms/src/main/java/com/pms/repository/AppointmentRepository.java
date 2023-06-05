package com.pms.repository;

import com.pms.entity.Appointment;
import com.pms.entity.Doctor;
import com.pms.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

  List<Appointment> findByPatient(Patient patient);

  @Query(value = "SELECT * FROM appointment WHERE status = :status AND patient_id = :id", nativeQuery = true)
  List<Appointment> findByStatusAndPatient(String status, Long id);

  List<Appointment> findByDoctor(Doctor doctor);

  @Query(value = "SELECT * FROM appointment WHERE status = :status AND doctor_id = :id", nativeQuery = true)
  List<Appointment> findByStatusAndDoctorId(String status, Long id);
 @Query(value = "SELECT id FROM appointment WHERE doctor_id = :id", nativeQuery = true)
  List<Long> findAppointmentIdByDoctorId(Long id);

 @Modifying
 @Transactional
  @Query(value = "INSERT INTO hospital_patient(hospital_id, patient_id) values(:hospitalId, :patientId)", nativeQuery = true)
  void savePatientHospital(Long hospitalId, Long patientId);
}
