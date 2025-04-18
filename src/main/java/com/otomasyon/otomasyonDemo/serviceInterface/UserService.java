package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User save(User theUser);
User update(Long id);
    void deleteById(Long id);
}
