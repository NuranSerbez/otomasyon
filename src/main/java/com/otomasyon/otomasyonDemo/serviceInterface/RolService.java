package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {
    List<Rol> findAll();

    Optional<Rol> findById(Long id);

    Rol save(Rol theRol);

    Rol update(Long id, Rol theRol);

    void deleteById(Long id);
}
