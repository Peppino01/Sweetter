package com.azienda.sweetter.repository;

import com.azienda.sweetter.model.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    @Override
    <S extends Role> S save(S entity);

    @Override
    Optional<Role> findById(Integer integer);

    Optional<Role> findByRole(String roleName);
}
