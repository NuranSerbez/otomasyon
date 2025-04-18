package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Ders;
import com.otomasyon.otomasyonDemo.repository.DersRepository;
import com.otomasyon.otomasyonDemo.repository.UserRepository;
import com.otomasyon.otomasyonDemo.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ders")
public class DersRestController {
    private DersRepository dersRepository;
    private UserRepository userRepository;
    private ProgramRepository programRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public DersRestController(DersRepository dersRepository, UserRepository userRepository, ProgramRepository programRepository, ObjectMapper objectMapper) {
        this.dersRepository = dersRepository;
        this.userRepository = userRepository;
        this.programRepository = programRepository;
        this.objectMapper = objectMapper;
    }


    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<Ders> findAll() {
        return dersRepository.findAll();
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public Ders getDers(@PathVariable Long id) {
        return dersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ders bulunamadı - " + id));
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public Ders addDers(@RequestBody Ders theDers) {
        theDers.setId(null);
        Ders dbDers = dersRepository.save(theDers);
        return dbDers;
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public Ders updateDers(@RequestBody Ders theDers) {
        Long dersId = theDers.getId();
        var ders = dersRepository.findById(dersId)
                .orElseThrow(() -> new RuntimeException("Ders bulunamadı: " + dersId));
        Long akademisyenId = theDers.getAkademisyen().getId();
        var akademisyen = userRepository.findById(akademisyenId)
                .orElseThrow(() -> new RuntimeException("Akademisyen bulunamadı: " + akademisyenId));
        Long programId = theDers.getProgram().getId();
        var program = programRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program bulunamadı: " + programId));
        ders.setDersAdi(theDers.getDersAdi());
        ders.setKontenjan(theDers.getKontenjan());
        ders.setAkademisyen(akademisyen);
        ders.setProgram(program);
        return dersRepository.save(ders);
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @DeleteMapping("/delete/{id}")
    public String deleteDers(@PathVariable Long id) {
        dersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ders bulunamadı - " + id));
        dersRepository.deleteById(id);
        return "Ders silindi - " + id;
    }
}
