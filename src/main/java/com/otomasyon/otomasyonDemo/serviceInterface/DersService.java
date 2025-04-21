package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Ders;

import java.util.List;
import java.util.Optional;

public interface DersService {
    List<Ders> findAll();

    Optional<Ders> findById(Long id);

    Ders save(Ders theDers);

    Ders update(Long id, Ders theDers);

    void deleteById(Long id);
}
