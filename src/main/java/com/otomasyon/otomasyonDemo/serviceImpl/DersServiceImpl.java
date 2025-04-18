package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Ders;
import com.otomasyon.otomasyonDemo.serviceInterface.DersService;

import java.util.List;

public class DersServiceImpl implements DersService {
    @Override
    public List<Ders> findAll() {
        return List.of();
    }

    @Override
    public Ders findById(Long id) {
        return null;
    }

    @Override
    public Ders save(Ders theDers) {
        return null;
    }

    @Override
    public Ders update(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
