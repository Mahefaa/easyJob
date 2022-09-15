package com.finalproject.easyjob.model;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "offerAndUser", columnNames = {"offer_id", "user_id"})})
public class Appliance implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @OneToOne
  private Offer offer;
  //1 user apply 1,n offer
  //1,n offer is applied by 1 user
  @OneToOne
  private User user;
  @Enumerated(EnumType.STRING)
  private Status status;

  @CreationTimestamp
  private Instant creationInstant;

  @Override
  public String toString() {
    return "Hello "
        + user.getEmail()
        + " Your Appliance to offer "
        + offer.getRef()
        + " got "
        + status;
  }

  public enum Status {
    ONGOING, APPROVED, REJECTED
  }
}
