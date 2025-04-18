package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Degerlendirme;

import java.util.List;

public interface DegerlendirmeService {
    List<Degerlendirme> findAll();

    Degerlendirme findById(Long id);

    Degerlendirme save(Degerlendirme theDegerlendirme);
Degerlendirme update(Long id);
    void deleteById(Long id);
}
