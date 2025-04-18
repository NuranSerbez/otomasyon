package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Ders;

import java.util.List;

public interface DersService {
    List<Ders> findAll();

    Ders findById(Long id);

    Ders save(Ders theDers);
Ders update(Long id);
    void deleteById(Long id);
}
