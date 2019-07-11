package com.mysong.api.service;


import com.mysong.api.model.User;

import java.util.List;

public interface UserService {

    User findByEmail(String email);

    List<User> findAll();

    <S extends User> S save(S s);

}
