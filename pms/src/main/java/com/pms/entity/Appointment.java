package com.pms.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Visibility;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date startDateAndTime;

  private Date  endDateAndTime;

  private String modeOfAppointment;

  private String status;

//  @OneToOne(optional = false)
//  @ManyToOne(fetch = FetchType.LAZY)
  @ManyToOne()
  private Patient patient;

  @ManyToOne()
//  @Column(name = "assignedTo")
  private Doctor doctor;

  @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<ScheduledAppointment> scheduledAppointment;
}
