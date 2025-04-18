package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Soru;
import com.otomasyon.otomasyonDemo.repository.SoruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soru")
public class SoruRestController {
    private SoruRepository soruRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public SoruRestController(SoruRepository soruRepository, ObjectMapper objectMapper) {
        this.soruRepository = soruRepository;
        this.objectMapper = objectMapper;
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<Soru> findAll() {
        return soruRepository.findAll();
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public Soru getSoru(@PathVariable Long id) {
        return soruRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soru bulunamadı - " + id));
    }
    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public Soru addSoru(@RequestBody Soru theSoru) {
        theSoru.setId(null);
        Soru dbSoru = soruRepository.save(theSoru);
        return dbSoru;
    }
    @PreAuthorize("hasRole('Idareci')")
    @PutMapping("/update/{id}")
    public Soru updateSoru(@PathVariable Long id, @RequestBody Soru theSoru) {
        Soru soru = soruRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soru bulunamadı: " + id));
        soru.setSorular(theSoru.getSorular());
        return soruRepository.save(soru);
    }
    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteSoru(@PathVariable Long id) {
        soruRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soru bulunamadı - " + id));
        soruRepository.deleteById(id);
        return "Soru silindi - " + id;
    }
}
