package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Fakulte;

import java.util.List;

public interface FakulteService {
    List<Fakulte> findAll();

    Fakulte findById(Long id);

    Fakulte save(Fakulte theFakulte);
Fakulte update(Long id);
    void deleteById(Long id);
}
