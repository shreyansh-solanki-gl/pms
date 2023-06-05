package com.pms.service;

import com.pms.entity.ScheduledAppointment;
import com.pms.entity.User;
import com.pms.repository.ScheduledAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduledAppointmentService {

  @Autowired
  ScheduledAppointmentRepository scheduledAppointmentRepository;

//  public ScheduledAppointment save(User user) {
//    ScheduledAppointment appointment = new ScheduledAppointment();
//    appointment.setStatus("active");
//    appointment.setAppointment(user.getPatient().getAppointment());
//
//    return scheduledAppointmentRepository.save(appointment);
//  }

  public ScheduledAppointment save(ScheduledAppointment scheduledAppointment) {
    scheduledAppointment.setStatus("active");
    scheduledAppointment.setAppointment(scheduledAppointment.getAppointment());
    return scheduledAppointmentRepository.save(scheduledAppointment);
  }

  public ScheduledAppointment update(ScheduledAppointment scheduledAppointment) {
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
    scheduledAppointment.setEndTime(Time.valueOf(formatter.format(date)));
    return scheduledAppointmentRepository.save(scheduledAppointment);
  }

  public List<ScheduledAppointment> findByAppointmentStatus(Long appointmentId, String status, User user) {
//    System.out.println(user.getPatient().getAppointment());
    return scheduledAppointmentRepository.findByStatusAndAppointment(status, appointmentId);
  }

//  public List<ScheduledAppointment> findByAppointment(User user) {
//    return scheduledAppointmentRepository.findByAppointment(user.getPatient().getAppointment().get(0).get);
//  }
}
