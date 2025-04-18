package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Not;
import com.otomasyon.otomasyonDemo.serviceInterface.NotService;

import java.util.List;

public class NotServiceImpl implements NotService {
    @Override
    public List<Not> findAll() {
        return List.of();
    }

    @Override
    public Not findById(Long id) {
        return null;
    }

    @Override
    public Not save(Not theNot) {
        return null;
    }

    @Override
    public Not update(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
