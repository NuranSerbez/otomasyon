package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Devamsizlik;

import java.util.List;

public interface DevamsizlikService {
    List<Devamsizlik> findAll();

    Devamsizlik findById(Long id);

    Devamsizlik save(Devamsizlik theDevamsizlik);
Devamsizlik update(Long id);
    void deleteById(Long id);
}
