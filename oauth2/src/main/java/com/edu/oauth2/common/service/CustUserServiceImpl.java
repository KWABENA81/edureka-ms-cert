package com.edu.oauth2.common.service;

import com.edu.oauth2.common.model.CustUser;
import com.edu.oauth2.common.model.Role;
import com.edu.oauth2.common.repo.CustUserRepo;
import com.edu.oauth2.common.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustUserServiceImpl implements CustUserService {

    private final CustUserRepo custUserRepo;

    private final RoleRepo roleRepo;

    @Override
    public CustUser saveUser(CustUser custUser) {
        log.info("Saving Customer {}", custUser);
        return custUserRepo.save(custUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving Role {}", role);
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToCustUser(String username, String rolename) {
        CustUser cuser = custUserRepo.findByUsername(username);
        log.info("Fetching Customer {}", cuser);
        Role role = roleRepo.findByName(rolename);
        log.info("Fetching Role {}", role);
        if (!cuser.getRoles().contains(role)) {
            log.info("Adding Role {} to Customer {}", role, cuser);
            cuser.getRoles().add(role);
        }
    }

    @Override
    public CustUser getCustUser(String username) {
        log.info("Fetching Customer by name {}", username);
        return custUserRepo.findByUsername(username);
    }

    @Override
    public Role getRole(String name) {
        log.info("Fetching Role by name {}", name);
        return roleRepo.findByName(name);
    }

    @Override
    public List<CustUser> getCustUsers() {
        log.info("Fetching All Customers ");
        return custUserRepo.findAll();
    }

    @Override
    public List<Role> getRoles() {
        log.info("Fetching All Roles ");
        return roleRepo.findAll();
    }
}
