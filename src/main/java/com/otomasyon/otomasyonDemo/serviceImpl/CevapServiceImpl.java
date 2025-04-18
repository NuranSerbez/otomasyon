package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Cevap;
import com.otomasyon.otomasyonDemo.serviceInterface.CevapService;

import java.util.List;

public class CevapServiceImpl implements CevapService {
    @Override
    public List<Cevap> findAll() {
        return List.of();
    }

    @Override
    public Cevap findById(Long id) {
        return null;
    }

    @Override
    public Cevap save(Cevap theCevap) {
        return null;
    }

    @Override
    public Cevap update(Long id) {
        return null;
    }


    @Override
    public void deleteById(Long id) {

    }
}
