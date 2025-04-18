package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.serviceInterface.ProgramService;

import java.util.List;

public class ProgramServiceImpl implements ProgramService {
    @Override
    public List<Program> findAll() {
        return List.of();
    }

    @Override
    public Program findById(Long id) {
        return null;
    }

    @Override
    public Program save(Program theProgram) {
        return null;
    }

    @Override
    public Program update(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
