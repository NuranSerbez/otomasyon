package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.DersAtama;

import java.util.List;
import java.util.Optional;

public interface DersAtamaService {
    List<DersAtama> findAll();

    Optional<DersAtama> findById(Long id);

    DersAtama save(DersAtama theDersAtama);

    DersAtama update(Long id, DersAtama theDersAtama);

    void deleteById(Long id);
}
