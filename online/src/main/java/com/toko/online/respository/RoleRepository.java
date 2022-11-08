package com.toko.online.respository;

import com.toko.online.model.entity.Roles;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
  Optional<Roles> findByRoleName(String roleName);
}
