package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Soru;
import com.otomasyon.otomasyonDemo.serviceInterface.SoruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soru")
public class SoruRestController {
    private SoruService soruService;
    private ObjectMapper objectMapper;

    @Autowired
    public SoruRestController(SoruService soruService, ObjectMapper objectMapper) {
        this.soruService = soruService;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<Soru> findAll() {
        return soruService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public Soru getSoru(@PathVariable Long id) {
        return soruService.findById(id)
                .orElseThrow(() -> new RuntimeException("Soru bulunamadı - " + id));
    }

    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public Soru addSoru(@RequestBody Soru theSoru) {
        theSoru.setId(null);
        Soru dbSoru = soruService.save(theSoru);
        return dbSoru;
    }

    @PreAuthorize("hasRole('Idareci')")
    @PutMapping("/update/{id}")
    public Soru updateSoru(@PathVariable Long id, @RequestBody Soru theSoru) {
        Soru soru = soruService.findById(id)
                .orElseThrow(() -> new RuntimeException("Soru bulunamadı: " + id));
        soru.setSorular(theSoru.getSorular());
        return soruService.save(soru);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteSoru(@PathVariable Long id) {
        soruService.findById(id)
                .orElseThrow(() -> new RuntimeException("Soru bulunamadı - " + id));
        soruService.deleteById(id);
        return "Soru silindi - " + id;
    }
}
