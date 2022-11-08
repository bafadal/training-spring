package com.toko.online.controller;

import com.toko.online.config.jwt.JwtUtils;
import com.toko.online.model.dto.LoginRequest;
import com.toko.online.model.dto.RegisterDto;
import com.toko.online.model.dto.Response;
import com.toko.online.model.entity.Roles;
import com.toko.online.model.entity.Users;
import com.toko.online.respository.RoleRepository;
import com.toko.online.respository.UsersRepository;
import com.toko.online.service.UsersDetailsImpl;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.management.relation.RoleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  public AuthenticationManager authenticationManager;

  @Autowired
  public UsersRepository usersRepository;

  @Autowired
  public RoleRepository roleRepository;

  @Autowired
  public JwtUtils jwtUtils;

  @Autowired
  private PasswordEncoder encoder;



  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UsersDetailsImpl userDetails = (UsersDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
    Response response  = new Response();
    response.setUsername(userDetails.getUsername());
    response.setToken(jwt);
    response.setRoles(roles);
    return ResponseEntity.ok(response);
  }




  @PostMapping("register")
  public ResponseEntity registerUser(@RequestBody RegisterDto users){
    if (!usersRepository.findByUsername(users.getUsername()).isEmpty()){
          return ResponseEntity.badRequest().body("User already registered");
    }

    if (usersRepository.getUserByEmail(users.getEmail()) != null) {
        return ResponseEntity.badRequest().body("User already registered");
    }

    Users user = new Users();
    user.setUsername(users.getUsername());
    user.setEmail(users.getEmail());
    user.setPassword(encoder.encode(users.getPassword()));

    Set<Roles> roles = users.getRoles();

    user.setRoles(roles);

    usersRepository.save(user);

    return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
  }

}
