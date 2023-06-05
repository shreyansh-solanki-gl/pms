package com.pms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne()
  private Appointment appointment;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User createdBy;

  private LocalDateTime createdDate;


  @Column(columnDefinition = "TEXT")
  private String text;
}
