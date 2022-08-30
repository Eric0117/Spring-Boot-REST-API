package com.example.demo.repository;

import com.example.demo.model.role.Role;
import com.example.demo.model.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
