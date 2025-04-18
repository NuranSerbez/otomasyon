package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Not;

import java.util.List;

public interface NotService {
    List<Not> findAll();

    Not findById(Long id);

    Not save(Not theNot);
Not update(Long id);
    void deleteById(Long id);
}
