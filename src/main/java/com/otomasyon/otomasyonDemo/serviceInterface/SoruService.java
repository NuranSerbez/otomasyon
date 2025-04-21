package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Soru;

import java.util.List;
import java.util.Optional;

public interface SoruService {
    List<Soru> findAll();

    Optional<Soru> findById(Long id);

    Soru save(Soru theSoru);

    Soru update(Long id, Soru theSoru);

    void deleteById(Long id);
}
