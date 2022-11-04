package com.test.project.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  UUID id;

  @Column(name = "name", nullable = false)
  String name;

  @Column(name = "surname", nullable = false)
  String surname;

  @Column(name = "birthday", nullable = false)
  Timestamp birthday;

  private Integer getAge() {
    return Math.abs(Period.between(LocalDate.now(),
        birthday.toLocalDateTime().toLocalDate()).getYears());
  }

  public UserDto getDto() {
    return new UserDto(name, surname, getAge());
  }
}
