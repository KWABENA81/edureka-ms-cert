package com.edu.oauth2.common.service;

import com.edu.oauth2.common.model.CustUser;
import com.edu.oauth2.common.model.Role;

import java.util.List;

public interface CustUserService {
    CustUser saveUser(CustUser custUser);

    Role saveRole(Role role);

    void addRoleToCustUser(String username, String rolename);

    CustUser getCustUser(String username);

    Role getRole(String name);

    List<CustUser> getCustUsers();

    List<Role> getRoles();
}
