package com.mysong.api.repository;

import com.mysong.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Override
    List<User> findAll();

    @Override
    <S extends User> S save(S s);

}
