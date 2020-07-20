package codegym.project.sdupenzu.service;

import codegym.project.sdupenzu.model.Role;
import codegym.project.sdupenzu.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName roleName);
}
