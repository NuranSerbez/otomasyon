package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Not;

import java.util.List;
import java.util.Optional;

public interface NotService {
    List<Not> findAll();

    Optional<Not> findById(Long id);

    Not save(Not theNot);

    Not update(Long id, Not theNot);

    void deleteById(Long id);
}
