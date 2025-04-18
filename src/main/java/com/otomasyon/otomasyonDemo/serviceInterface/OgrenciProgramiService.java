package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.OgrenciProgrami;

import java.util.List;

public interface OgrenciProgramiService {
    List<OgrenciProgrami> findAll();

    OgrenciProgrami findById(Long id);

    OgrenciProgrami save(OgrenciProgrami theOgrenciProgrami);
OgrenciProgrami update(Long id);
    void deleteById(Long id);
}
