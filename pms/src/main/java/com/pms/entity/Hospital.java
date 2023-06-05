package com.pms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
public class Hospital {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String address;
  private int contactNo;
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private User user;



  @OneToMany(mappedBy = "hospital", fetch = FetchType.EAGER)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Doctor> doctor;
  @ManyToMany(cascade = CascadeType.ALL)
  private List<Patient> patient;

}
