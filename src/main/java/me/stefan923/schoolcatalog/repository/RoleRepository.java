package me.stefan923.schoolcatalog.repository;

import me.stefan923.schoolcatalog.domain.Role;
import me.stefan923.schoolcatalog.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByTypeEquals(RoleType type);

}
