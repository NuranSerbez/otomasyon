package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.serviceInterface.RolService;

import java.util.List;

public class RolServiceImpl implements RolService {
    @Override
    public List<Rol> findAll() {
        return List.of();
    }

    @Override
    public Rol findById(Long id) {
        return null;
    }

    @Override
    public Rol save(Rol theRol) {
        return null;
    }

    @Override
    public Rol update(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
