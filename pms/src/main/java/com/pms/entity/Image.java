package com.pms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="images")
@Data
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Lob
  private byte[] image;

  @OneToOne(mappedBy = "image", cascade = CascadeType.ALL)
  private User user;
}
