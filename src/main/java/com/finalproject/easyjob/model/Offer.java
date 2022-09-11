package com.finalproject.easyjob.model;


import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull(message = "Offer ref is mandatory")
  @NotBlank(message = "Offer ref is mandatory")
  @Column(unique = true)
  private String ref;

  @ManyToOne
  @NotNull(message = "Offer Domain is mandatory")
  private Domain domain;

  @NotNull(message = "Offer position is mandatory")
  @NotBlank(message = "Offer position is mandatory")
  private String position;

  @NotNull(message = "Offer mission is mandatory")
  @NotBlank(message = "Offer mission is mandatory")
  private String mission;

  @NotNull(message = "Offer profile is mandatory")
  @NotBlank(message = "Offer profile is mandatory")
  private String profile;

  @NotNull(message = "Offer location is mandatory")
  @NotBlank(message = "Offer location is mandatory")
  private String location;
  @CreationTimestamp
  private Instant creationInstant;

  @Enumerated(EnumType.STRING)
  private Status status;

  @ManyToOne
  private User sender;
  public enum Status {
    AVAILABLE,TAKEN
  }
}
