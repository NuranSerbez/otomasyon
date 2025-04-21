package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Program;

import java.util.List;
import java.util.Optional;

public interface ProgramService {
    List<Program> findAll();

    Optional<Program> findById(Long id);

    Program save(Program theProgram);

    Program update(Long id, Program theProgram);

    void deleteById(Long id);
}
