package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.DersAtama;

import java.util.List;

public interface DersAtamaService {
    List<DersAtama> findAll();

    DersAtama findById(Long id);

    DersAtama save(DersAtama theDersAtama);
DersAtama update(Long id);
    void deleteById(Long id);
}
