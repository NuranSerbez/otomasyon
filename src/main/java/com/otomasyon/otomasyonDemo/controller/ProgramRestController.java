package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Bolum;
import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.repository.BolumRepository;
import com.otomasyon.otomasyonDemo.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/program")
public class ProgramRestController {
    private ProgramRepository programRepository;
    private BolumRepository bolumRepository;
    private ObjectMapper objectMapper;
    @Autowired
    public ProgramRestController(ProgramRepository programRepository, BolumRepository bolumRepository, ObjectMapper objectMapper) {
        this.programRepository = programRepository;
        this.bolumRepository = bolumRepository;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/all")
    public List<Program> findAll() {
        return programRepository.findAll();
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public Program getProgram(@PathVariable Long id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program bulunamadı - " + id));
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public Program addProgram(@RequestBody Program theProgram) {
        theProgram.setId(null);
        Program dbProgram = programRepository.save(theProgram);
        return dbProgram;
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public Program updateProgram(@PathVariable Long id, @RequestBody Program theProgram) {
        var program = programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program bulunamadı: " + id));

        List<Bolum> bolumler = theProgram.getBolumler().stream()
                .map(b -> bolumRepository.findById(b.getId())
                        .orElseThrow(() -> new RuntimeException("Bölüm bulunamadı: " + b.getId())))
                .toList();
        program.setProgramTuru(theProgram.getProgramTuru());
        program.setBolumler(bolumler);

        return programRepository.save(program);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/{id}")
    public String deleteProgram(@PathVariable Long id) {
        programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program bulunamadı - " + id));
        programRepository.deleteById(id);
        return "Program silindi - " + id;
    }
}
