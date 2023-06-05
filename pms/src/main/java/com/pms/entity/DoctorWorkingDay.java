package com.pms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class DoctorWorkingDay {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate date;

  private LocalTime startTime;

  private LocalTime endTime;

  @ManyToOne(optional = false)
  private Doctor doctor;

}
