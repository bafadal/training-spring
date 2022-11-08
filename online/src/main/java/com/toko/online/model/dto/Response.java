package com.toko.online.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class Response {
  private String token;
  private String username;
  private List<String> roles;
}
