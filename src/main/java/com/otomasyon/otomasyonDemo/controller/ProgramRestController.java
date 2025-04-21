package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Bolum;
import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.serviceInterface.BolumService;
import com.otomasyon.otomasyonDemo.serviceInterface.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/program")
public class ProgramRestController {
    private ProgramService programService;
    private BolumService bolumService;
    private ObjectMapper objectMapper;

    @Autowired
    public ProgramRestController(ProgramService programService, BolumService bolumService, ObjectMapper objectMapper) {
        this.programService = programService;
        this.bolumService = bolumService;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/all")
    public List<Program> findAll() {
        return programService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public Program getProgram(@PathVariable Long id) {
        return programService.findById(id)
                .orElseThrow(() -> new RuntimeException("Program bulunamadı - " + id));
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public Program addProgram(@RequestBody Program theProgram) {
        theProgram.setId(null);
        Program dbProgram = programService.save(theProgram);
        return dbProgram;
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public Program updateProgram(@PathVariable Long id, @RequestBody Program theProgram) {
        var program = programService.findById(id)
                .orElseThrow(() -> new RuntimeException("Program bulunamadı: " + id));

        List<Bolum> bolumler = theProgram.getBolumler().stream()
                .map(b -> bolumService.findById(b.getId())
                        .orElseThrow(() -> new RuntimeException("Bölüm bulunamadı: " + b.getId())))
                .toList();
        program.setProgramTuru(theProgram.getProgramTuru());
        program.setBolumler(bolumler);

        return programService.save(program);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/{id}")
    public String deleteProgram(@PathVariable Long id) {
        programService.findById(id)
                .orElseThrow(() -> new RuntimeException("Program bulunamadı - " + id));
        programService.deleteById(id);
        return "Program silindi - " + id;
    }
}
