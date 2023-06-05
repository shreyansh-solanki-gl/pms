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
public class Patient {

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

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private User user;

  @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Appointment> appointment;

  @ManyToMany(mappedBy = "patient", cascade = CascadeType.MERGE)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Hospital> hospital;


}
