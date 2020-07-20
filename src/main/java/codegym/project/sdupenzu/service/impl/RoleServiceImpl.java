package codegym.project.sdupenzu.service.impl;

import codegym.project.sdupenzu.model.Role;
import codegym.project.sdupenzu.model.RoleName;
import codegym.project.sdupenzu.repository.IRoleRepository;
import codegym.project.sdupenzu.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
