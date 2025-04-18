package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Program;

import java.util.List;

public interface ProgramService {
    List<Program> findAll();

    Program findById(Long id);

    Program save(Program theProgram);

    Program update(Long id);

    void deleteById(Long id);
}
