package com.edu.oauth2.common.repo;

import com.edu.oauth2.common.model.CustUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustUserRepo extends JpaRepository<CustUser,Long> {
    CustUser findByUsername(String username);
}
