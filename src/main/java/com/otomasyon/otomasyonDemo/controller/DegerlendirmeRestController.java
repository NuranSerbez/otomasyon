package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Degerlendirme;
import com.otomasyon.otomasyonDemo.repository.DegerlendirmeRepository;
import com.otomasyon.otomasyonDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/degerlendirme")
public class DegerlendirmeRestController {
    private DegerlendirmeRepository degerlendirmeRepository;
    private UserRepository userRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public DegerlendirmeRestController(DegerlendirmeRepository degerlendirmeRepository, UserRepository userRepository, ObjectMapper objectMapper) {
        this.degerlendirmeRepository = degerlendirmeRepository;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasRole('Idareci')")
    @GetMapping("/all")
    public List<Degerlendirme> findAll(){
        return degerlendirmeRepository.findAll();
    }
    @PreAuthorize("hasRole('Idareci')")
    @GetMapping("/id/{id}")
    public Degerlendirme getDegerlendirme(@PathVariable Long id){
        return degerlendirmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Değerlendirme bulunamadı - " + id));
    }
    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public Degerlendirme addDegerlendirme(@RequestBody Degerlendirme theDegerlendirme){
        theDegerlendirme.setId(null);
        Degerlendirme dbDegerlendirme = degerlendirmeRepository.save(theDegerlendirme);
        return dbDegerlendirme;
    }
    @PreAuthorize("hasRole('Idareci')")
    @PutMapping("/update/{id}")
    public Degerlendirme updateDegerlendirme(@PathVariable Long id, @RequestBody Degerlendirme theDegerlendirme) {
        var degerlendirme = degerlendirmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Değerlendirme bulunamadı: " + id));
        Long akademisyenId = theDegerlendirme.getAkademisyen().getId();
        var akademisyen = userRepository.findById(akademisyenId)
                .orElseThrow(() -> new RuntimeException("Akademisyen bulunamadı: " + akademisyenId));
        Long ogrenciId = theDegerlendirme.getOgrenci().getId();
        var ogrenci = userRepository.findById(ogrenciId)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı: " + ogrenciId));
        degerlendirme.setAkademisyen(akademisyen);
        degerlendirme.setOgrenci(ogrenci);
        degerlendirme.setOrtalama(theDegerlendirme.getOrtalama());
        return degerlendirmeRepository.save(degerlendirme);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteDegerlendirme(@PathVariable Long id){
        degerlendirmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Değerlendirme bulunamadı - " + id));
        degerlendirmeRepository.deleteById(id);
        return "Değerlendirme silindi - " + id;
    }
}
