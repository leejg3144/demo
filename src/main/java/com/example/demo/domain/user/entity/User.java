package com.example.demo.domain.user.entity;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.common.entity.enums.BaseStatus;
import com.example.demo.domain.user.entity.enums.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USER_INFO")
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_SEQ", length = 25)
  private Long id;

  @Column(nullable = false)
  private String userId;

  @Column(name = "name", length = 50)
  private String name;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @NotNull
  @Enumerated(EnumType.STRING)
  private BaseStatus state;

  private LocalDateTime lastLoggedInAt;

  public void loginDate() {
    this.lastLoggedInAt = LocalDateTime.now();
  }

}