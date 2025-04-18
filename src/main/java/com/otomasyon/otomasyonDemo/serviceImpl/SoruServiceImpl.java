package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Soru;
import com.otomasyon.otomasyonDemo.serviceInterface.SoruService;

import java.util.List;

public class SoruServiceImpl implements SoruService {
    @Override
    public List<Soru> findAll() {
        return List.of();
    }

    @Override
    public Soru findById(Long id) {
        return null;
    }

    @Override
    public Soru save(Soru theSoru) {
        return null;
    }

    @Override
    public Soru update(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
