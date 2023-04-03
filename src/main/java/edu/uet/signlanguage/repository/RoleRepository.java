package edu.uet.signlanguage.repository;

import edu.uet.signlanguage.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Object> findByName(String roleUser);
}
