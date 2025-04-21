package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Fakulte;

import java.util.List;
import java.util.Optional;

public interface FakulteService {
    List<Fakulte> findAll();
    Optional<Fakulte> findById(Long id);
    Fakulte save(Fakulte theFakulte);
    Fakulte update(Long id, Fakulte theFakulte);
    void deleteById(Long id);
}

