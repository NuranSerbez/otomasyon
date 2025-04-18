package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Rol;

import java.util.List;

public interface RolService {
    List<Rol> findAll();

    Rol findById(Long id);

    Rol save(Rol theRol);
Rol update(Long id);
    void deleteById(Long id);
}
