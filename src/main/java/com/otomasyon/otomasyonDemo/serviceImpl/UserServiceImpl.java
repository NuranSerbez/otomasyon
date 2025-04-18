package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.serviceInterface.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User save(User theUser) {
        return null;
    }

    @Override
    public User update(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
