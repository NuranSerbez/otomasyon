package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Fakulte;
import com.otomasyon.otomasyonDemo.serviceInterface.FakulteService;

import java.util.List;

public class FakulteServiceImpl implements FakulteService {
    @Override
    public List<Fakulte> findAll() {
        return List.of();
    }

    @Override
    public Fakulte findById(Long id) {
        return null;
    }

    @Override
    public Fakulte save(Fakulte theFakulte) {
        return null;
    }

    @Override
    public Fakulte update(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
