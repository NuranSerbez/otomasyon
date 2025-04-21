package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Bolum;
import com.otomasyon.otomasyonDemo.repository.BolumRepository;

import java.util.List;
import java.util.Optional;

public interface BolumService {
    List<Bolum> findAll();

    Optional<Bolum> findById(Long id);

    Bolum save(Bolum theBolum);

    Bolum update(Long id, Bolum theBolum);

    BolumRepository deleteById(Long id);
}
