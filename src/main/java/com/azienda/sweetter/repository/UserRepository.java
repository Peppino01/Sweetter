package com.azienda.sweetter.repository;

import com.azienda.sweetter.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Override
    <S extends User> S save(S entity);

    Optional<User> findByIdAndIsActiveTrue(Integer integer);

    Optional<User> findByUsernameAndIsActiveTrue(String username);

    Optional<User> findByEmailAndIsActiveTrue(String email);
}
