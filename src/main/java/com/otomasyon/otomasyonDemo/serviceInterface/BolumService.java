package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Bolum;

import java.util.List;

public interface BolumService {
    List<Bolum> findAll();

    Bolum findById(Long id);

    Bolum save(Bolum theBolum);

    Bolum update(Long id);

    void deleteById(Long id);
}
