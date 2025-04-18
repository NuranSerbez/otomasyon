package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Cevap;

import java.util.List;

public interface CevapService {
    List<Cevap> findAll();

    Cevap findById(Long id);

    Cevap save(Cevap theCevap);
    Cevap update(Long id);

    void deleteById(Long id);
}
