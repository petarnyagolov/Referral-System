package com.infinno.services;

import com.infinno.entities.Role;
import com.infinno.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
     private static final String  DEFAULT_ROLE = "ROLE_USER";

     private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getDefaultRole() {
        Role role=this.roleRepository.findOneByAuthority(DEFAULT_ROLE);
        return role;
    }

}
