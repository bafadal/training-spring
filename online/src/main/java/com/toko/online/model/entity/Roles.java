package com.toko.online.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Roles {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int roleId;

  private String roleName;

}
