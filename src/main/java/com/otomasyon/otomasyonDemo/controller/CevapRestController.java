package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Cevap;
import com.otomasyon.otomasyonDemo.repository.CevapRepository;
import com.otomasyon.otomasyonDemo.repository.DegerlendirmeRepository;
import com.otomasyon.otomasyonDemo.repository.SoruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cevap")
public class CevapRestController {
    private CevapRepository cevapRepository;
    private DegerlendirmeRepository degerlendirmeRepository;
    private SoruRepository soruRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public CevapRestController(CevapRepository cevapRepository, DegerlendirmeRepository degerlendirmeRepository, SoruRepository soruRepository, ObjectMapper objectMapper) {
        this.cevapRepository = cevapRepository;
        this.degerlendirmeRepository = degerlendirmeRepository;
        this.soruRepository = soruRepository;
        this.objectMapper = objectMapper;
    }


    @PreAuthorize("hasAnyRole('Akademisyen','Idareci')")
    @GetMapping("/all")
    public List<Cevap> findAll() {
        return cevapRepository.findAll();
    }
    @PreAuthorize("hasAnyRole('Akademisyen','Idareci')")
    @GetMapping("/id/{id}")
    public Cevap getCevap(@PathVariable Long id) {
        return cevapRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cevap bulunamadı - " + id));
    }
    @PreAuthorize("hasAnyRole('Ogrenci','Idareci')")
    @PostMapping("/add")
    public Cevap addCevap(@RequestBody Cevap theCevap) {
        theCevap.setId(null);
        Cevap dbCevap = cevapRepository.save(theCevap);
        return dbCevap;
    }
    @PreAuthorize("hasAnyRole('Ogrenci','Idareci')")
    @PutMapping("/update/{id}")
    public Cevap updateCevap(@RequestBody Cevap theCevap) {
        Long cevapId = theCevap.getId();
        var cevap = cevapRepository.findById(cevapId)
                .orElseThrow(() -> new RuntimeException("Cevap bulunamadı: " + cevapId));
        Long degerlendirmeId = theCevap.getDegerlendirme().getId();
        var degerlendirme = degerlendirmeRepository.findById(degerlendirmeId)
                .orElseThrow(() -> new RuntimeException("Degerlendirme bulunamadı: " + degerlendirmeId));
        Long soruId = theCevap.getSoru().getId();
        var soru = soruRepository.findById(soruId)
                .orElseThrow(() -> new RuntimeException("Soru bulunamadı: " + soruId));
        cevap.setDegerlendirme(degerlendirme);
        cevap.setSoru(soru);
        cevap.setPuan(theCevap.getPuan());
        return cevapRepository.save(cevap);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteCevap(@PathVariable Long id) {
        cevapRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cevap bulunamadı - " + id));
        cevapRepository.deleteById(id);
        return "Cevap silindi - " + id;
    }
}