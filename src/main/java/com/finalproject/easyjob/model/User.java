package com.finalproject.easyjob.model;

import com.finalproject.easyjob.security.model.Role;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "\"user\"")
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @NotBlank(message = "email cannot be blank")
  @NotNull(message = "email is mandatory")
  private String email;
  @NotBlank(message = "password cannot be blank")
  @NotNull(message = "password is mandatory")
  private String password;
  @OneToMany
  private Set<Domain> interests;
  @CreationTimestamp
  private Instant joinedInstant;
  @Enumerated(EnumType.STRING)
  private Role role;
  @Column(columnDefinition = "boolean default true")
  private Boolean enabled;
}
