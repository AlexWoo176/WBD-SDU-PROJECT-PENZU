package codegym.project.sdupenzu.repository;

import codegym.project.sdupenzu.model.Role;
import codegym.project.sdupenzu.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
