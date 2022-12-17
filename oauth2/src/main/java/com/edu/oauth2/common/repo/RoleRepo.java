package com.edu.oauth2.common.repo;

import com.edu.oauth2.common.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
