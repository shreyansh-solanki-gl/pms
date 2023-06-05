package com.pms.repository;

import com.pms.entity.Appointment;
import com.pms.entity.ScheduledAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ScheduledAppointmentRepository extends JpaRepository<ScheduledAppointment, Long> {

//  List<ScheduledAppointment> findByAppointment(Appointment appointment);
  @Query(value = "SELECT * FROM scheduled_appointment WHERE status = :status AND appointment_id = :id", nativeQuery = true)
  List<ScheduledAppointment> findByStatusAndAppointment(String status, Long id);

  @Query(value = "SELECT * FROM scheduled_appointment WHERE appointment_id = :id", nativeQuery = true)
  ScheduledAppointment findByAppointmentId(Long id);

  @Query(value = "SELECT * FROM scheduled_appointment WHERE status = 'active' AND appointment_id = :appointmentId", nativeQuery = true)
  ScheduledAppointment findActiveScheduledAppointmentByAppointmentId(Long appointmentId);

  @Query(value = "SELECT * FROM scheduled_appointment WHERE status = 'closed' AND appointment_id = :appointmentId", nativeQuery = true)
  ScheduledAppointment findClosedScheduledAppointmentByAppointmentId(Long appointmentId);
}
