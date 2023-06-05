package com.pms.repository;

import com.pms.entity.Doctor;
import com.pms.entity.DoctorWorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorWorkingDayRepository extends JpaRepository<DoctorWorkingDay, Long> {

  List<DoctorWorkingDay> findByDoctor(Doctor doctor);

  @Query(value = "SELECT * FROM doctor_working_day WHERE doctor_id = :id", nativeQuery = true)
  List<DoctorWorkingDay> findByDoctorId(Long id);
}
