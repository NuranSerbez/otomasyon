package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Degerlendirme;

import java.util.List;
import java.util.Optional;

public interface DegerlendirmeService {
    List<Degerlendirme> findAll();

    Optional<Degerlendirme> findById(Long id);

    Degerlendirme save(Degerlendirme theDegerlendirme);

    Degerlendirme update(Long id, Degerlendirme theDegerlendirme);

    void deleteById(Long id);
}
