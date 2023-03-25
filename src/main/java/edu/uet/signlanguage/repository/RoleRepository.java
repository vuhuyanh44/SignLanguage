package edu.uet.signlanguage.repository;

import edu.uet.signlanguage.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Object> findByName(String roleUser);
}
