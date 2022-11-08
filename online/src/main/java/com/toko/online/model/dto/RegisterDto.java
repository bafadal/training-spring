package com.toko.online.model.dto;

import com.toko.online.model.entity.Roles;
import java.util.Set;
import lombok.Data;


@Data
public class RegisterDto {

  private String username;
  private String password;
  private String email;
  private Set<Roles> roles;
}
