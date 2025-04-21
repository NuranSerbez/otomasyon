package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User theUser);

    User update(Long id, User theUser);

    void deleteById(Long id);
}
