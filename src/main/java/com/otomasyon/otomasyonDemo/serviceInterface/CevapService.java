package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Cevap;

import java.util.List;
import java.util.Optional;

public interface CevapService {
    List<Cevap> findAll();

    Optional<Cevap> findById(Long id);

    Cevap save(Cevap theCevap);

    Cevap update(Long id, Cevap theCevap);

    void deleteById(Long id);
}
