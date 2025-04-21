package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.repository.ProgramRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;

    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository, ProgramRepository programRepository1) {
        this.programRepository = programRepository1;
    }

    @Override
    public List<Program> findAll() {
        return programRepository.findAll();
    }

    @Override
    public Optional<Program> findById(Long id) {
        return programRepository.findById(id);
    }

    @Override
    public Program save(Program theProgram) {
        return programRepository.save(theProgram);
    }

    @Override
    public Program update(Long id, Program theProgram) {
        return programRepository.save(theProgram);
    }

    @Override
    public void deleteById(Long id) {
        if (programRepository.existsById(id)) {
            programRepository.deleteById(id);
        }
    }
}
