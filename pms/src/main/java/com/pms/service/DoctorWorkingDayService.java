package com.pms.service;

import com.pms.entity.Doctor;
import com.pms.entity.DoctorWorkingDay;
import com.pms.entity.User;
import com.pms.repository.DoctorWorkingDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorWorkingDayService {

  @Autowired
  DoctorWorkingDayRepository doctorWorkingDayRepository;

  public DoctorWorkingDay save(DoctorWorkingDay doctorWorkingDay, User user) {
    doctorWorkingDay.setDoctor(user.getDoctor());
    return doctorWorkingDayRepository.save(doctorWorkingDay);
  }

  public List<DoctorWorkingDay> findByDoctor(Doctor doctor){
    return doctorWorkingDayRepository.findByDoctor(doctor);
  }

  public List<DoctorWorkingDay> getAll(Long doctorId) {
    return doctorWorkingDayRepository.findByDoctorId(doctorId);
  }
}
