package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Ders;
import com.otomasyon.otomasyonDemo.serviceInterface.DersService;
import com.otomasyon.otomasyonDemo.serviceInterface.ProgramService;
import com.otomasyon.otomasyonDemo.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ders")
public class DersRestController {
    private DersService dersService;
    private UserService userService;
    private ProgramService programService;
    private ObjectMapper objectMapper;

    @Autowired
    public DersRestController(DersService dersService, UserService userService, ProgramService programService, ObjectMapper objectMapper) {
        this.dersService = dersService;
        this.userService = userService;
        this.programService = programService;
        this.objectMapper = objectMapper;
    }


    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<Ders> findAll() {
        return dersService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public Ders getDers(@PathVariable Long id) {
        return dersService.findById(id)
                .orElseThrow(() -> new RuntimeException("Ders bulunamadı - " + id));
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public Ders addDers(@RequestBody Ders theDers) {
        theDers.setId(null);
        Ders dbDers = dersService.save(theDers);
        return dbDers;
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public Ders updateDers(@RequestBody Ders theDers) {
        Long dersId = theDers.getId();
        var ders = dersService.findById(dersId)
                .orElseThrow(() -> new RuntimeException("Ders bulunamadı: " + dersId));
        Long akademisyenId = theDers.getAkademisyen().getId();
        var akademisyen = userService.findById(akademisyenId)
                .orElseThrow(() -> new RuntimeException("Akademisyen bulunamadı: " + akademisyenId));
        Long programId = theDers.getProgram().getId();
        var program = programService.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program bulunamadı: " + programId));
        ders.setDersAdi(theDers.getDersAdi());
        ders.setKontenjan(theDers.getKontenjan());
        ders.setAkademisyen(akademisyen);
        ders.setProgram(program);
        return dersService.save(ders);
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @DeleteMapping("/delete/{id}")
    public String deleteDers(@PathVariable Long id) {
        dersService.findById(id)
                .orElseThrow(() -> new RuntimeException("Ders bulunamadı - " + id));
        dersService.deleteById(id);
        return "Ders silindi - " + id;
    }
}
