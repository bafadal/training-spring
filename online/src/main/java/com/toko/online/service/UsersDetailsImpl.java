package com.toko.online.service;


import com.toko.online.model.entity.Users;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UsersDetailsImpl implements UserDetails {

  private int id;
  private String username;
  private String password;
  private String email;

  private Collection<? extends GrantedAuthority> authorities;


  public UsersDetailsImpl(int id, String username, String password, String email,
                          Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.authorities = authorities;
  }

  public static UsersDetailsImpl build(Users users){
    String ROLE_PREFIX = "ROLE_";
    List<GrantedAuthority> authorities = users.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX+role.getRoleName()))
        .collect(Collectors.toList());

    return new UsersDetailsImpl(
        users.getUserId(),
        users.getUsername(),
        users.getPassword(),
        users.getEmail(),
        authorities);

  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
