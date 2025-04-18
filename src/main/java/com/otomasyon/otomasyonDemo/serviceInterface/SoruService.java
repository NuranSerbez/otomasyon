package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Soru;

import java.util.List;

public interface SoruService {
    List<Soru> findAll();

    Soru findById(Long id);

    Soru save(Soru theSoru);
Soru update(Long id);
    void deleteById(Long id);
}
