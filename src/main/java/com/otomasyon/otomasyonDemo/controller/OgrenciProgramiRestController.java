package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.OgrenciProgrami;
import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.repository.UserRepository;
import com.otomasyon.otomasyonDemo.repository.ProgramRepository;
import com.otomasyon.otomasyonDemo.repository.OgrenciProgramiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ogrenci_programi")
public class OgrenciProgramiRestController {
    private OgrenciProgramiRepository ogrenciProgramiRepository;
    private UserRepository userRepository;
    private ProgramRepository programRepository;
    private ObjectMapper objectMapper;
    @Autowired
    public OgrenciProgramiRestController(OgrenciProgramiRepository ogrenciProgramiRepository, UserRepository userRepository, ProgramRepository programRepository, ObjectMapper objectMapper) {
        this.ogrenciProgramiRepository = ogrenciProgramiRepository;
        this.userRepository = userRepository;
        this.programRepository = programRepository;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<OgrenciProgrami> findAll() {
        return ogrenciProgramiRepository.findAll();
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public OgrenciProgrami getOgrenciProgrami(@PathVariable Long id) {
        return ogrenciProgramiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci Programı bulunamadı - " + id));
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public OgrenciProgrami addOgrenciProgrami(@RequestBody OgrenciProgrami theOgrenciProgrami) {
        theOgrenciProgrami.setId(null);
        OgrenciProgrami dbOgrenciProgrami = ogrenciProgramiRepository.save(theOgrenciProgrami);
        return dbOgrenciProgrami;
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public OgrenciProgrami updateOgrenciProgrami(@PathVariable Long id, @RequestBody OgrenciProgrami theOgrenciProgrami) {
        var ogrenciProgrami = ogrenciProgramiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci programı bulunamadı: " + id));
        List<User> ogrenciler = theOgrenciProgrami.getOgrenciler().stream()
                .map(o -> userRepository.findById(o.getId())
                        .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı: " + o.getId())))
                .toList();
        List<Program> programlar = theOgrenciProgrami.getProgramlar().stream()
                .map(p -> programRepository.findById(p.getId())
                        .orElseThrow(() -> new RuntimeException("Program bulunamadı: " + p.getId())))
                .toList();
        ogrenciProgrami.setOgrenciler(ogrenciler);
        ogrenciProgrami.setProgramlar(programlar);
        return ogrenciProgramiRepository.save(ogrenciProgrami);
    }

    @PreAuthorize("hasRole('ROLE_Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteOgrenciProgrami(@PathVariable Long id) {
        ogrenciProgramiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci programı bulunamadı - " + id));
        ogrenciProgramiRepository.deleteById(id);
        return "Öğrenci programı silindi - " + id;
    }
}
