package com.toko.online.respository;

import com.toko.online.model.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
  Users getUserByEmail(String email);
  Optional<Users> findByUsername(String username);
}
