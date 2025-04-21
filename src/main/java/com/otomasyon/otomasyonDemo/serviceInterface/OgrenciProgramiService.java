package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.OgrenciProgrami;

import java.util.List;
import java.util.Optional;

public interface OgrenciProgramiService {
    List<OgrenciProgrami> findAll();

    Optional <OgrenciProgrami> findById(Long id);

    OgrenciProgrami save(OgrenciProgrami theOgrenciProgrami);

    OgrenciProgrami update(Long id, OgrenciProgrami theOgrenciProgrami);

    void deleteById(Long id);
}
