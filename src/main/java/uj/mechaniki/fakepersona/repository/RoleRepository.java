package uj.mechaniki.fakepersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uj.mechaniki.fakepersona.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role getRoleById(Long id);
    Role getRoleByName(String name);
}
