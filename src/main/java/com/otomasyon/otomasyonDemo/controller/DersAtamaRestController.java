package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.DersAtama;
import com.otomasyon.otomasyonDemo.repository.DersRepository;
import com.otomasyon.otomasyonDemo.repository.UserRepository;
import com.otomasyon.otomasyonDemo.repository.DersAtamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ders_atama")
public class DersAtamaRestController {
    private DersAtamaRepository dersAtamaRepository;
    private UserRepository userRepository;
    private DersRepository dersRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public DersAtamaRestController(DersAtamaRepository dersAtamaRepository, UserRepository userRepository, DersRepository dersRepository, ObjectMapper objectMapper) {
        this.dersAtamaRepository = dersAtamaRepository;
        this.userRepository = userRepository;
        this.dersRepository = dersRepository;
        this.objectMapper = objectMapper;
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @GetMapping("/all")
    public List<DersAtama>findAll(){
        return dersAtamaRepository.findAll();
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @GetMapping("/id/{id}")
    public DersAtama getDersAtama(@PathVariable Long id) {
        return dersAtamaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ders atama bulunamadı - " + id));
    }
    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public DersAtama addDersAtama(@RequestBody DersAtama theDersAtama){
        theDersAtama.setId(null);
        DersAtama dbDersAtama = dersAtamaRepository.save(theDersAtama);
        return dbDersAtama;
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public DersAtama updateDersAtama(@PathVariable Long id, @RequestBody DersAtama theDersAtama) {
        var dersAtama = dersAtamaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DersAtama bulunamadı: " + id));
        Long dersId = theDersAtama.getDers().getId();
        var ders = dersRepository.findById(dersId)
                .orElseThrow(() -> new RuntimeException("Ders bulunamadı: " + dersId));
        Long ogrenciId = theDersAtama.getOgrenci().getId();
        var ogrenci = userRepository.findById(ogrenciId)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı: " + ogrenciId));
        dersAtama.setDers(ders);
        dersAtama.setOgrenci(ogrenci);
        dersAtama.setOnaydurum(theDersAtama.isOnaydurum());
        return dersAtamaRepository.save(dersAtama);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteDersAtama(@PathVariable Long id) {
        dersAtamaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ders atama bulunamadı - " + id));
        dersAtamaRepository.deleteById(id);
        return "Ders atama silindi - " + id;
    }
}
