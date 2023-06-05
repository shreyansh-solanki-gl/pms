package com.pms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Doctor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstname;

  private String lastname;

  private String email;

  private LocalDate dob;

  private Long contactNumber;

  private String address;

  private String gender;

  private int yearOfExperience;

  private String speciality;

  private String currentWorkingAddress;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private User user;

  @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Appointment> appointment;

  @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<DoctorWorkingDay> doctorWorkingDays;

  @ManyToOne(cascade = CascadeType.MERGE)
  private Hospital hospital;



}
