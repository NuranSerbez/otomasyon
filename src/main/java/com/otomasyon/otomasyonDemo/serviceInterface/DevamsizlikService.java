package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Devamsizlik;

import java.util.List;
import java.util.Optional;

public interface DevamsizlikService {
    List<Devamsizlik> findAll();

    Optional<Devamsizlik> findById(Long id);

    Devamsizlik save(Devamsizlik theDevamsizlik);

    Devamsizlik update(Long id, Devamsizlik theDevamsizlik);

    void deleteById(Long id);
}
