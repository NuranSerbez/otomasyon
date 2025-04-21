package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Cevap;
import com.otomasyon.otomasyonDemo.entity.Degerlendirme;
import com.otomasyon.otomasyonDemo.entity.Soru;
import com.otomasyon.otomasyonDemo.serviceInterface.CevapService;
import com.otomasyon.otomasyonDemo.serviceInterface.DegerlendirmeService;
import com.otomasyon.otomasyonDemo.serviceInterface.SoruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/cevap")
public class CevapRestController {
    private CevapService cevapService;
    private DegerlendirmeService degerlendirmeService;
    private SoruService soruService;
    private ObjectMapper objectMapper;

    @Autowired
    public CevapRestController(CevapService cevapService, DegerlendirmeService degerlendirmeService, SoruService soruService, ObjectMapper objectMapper) {
        this.cevapService = cevapService;
        this.degerlendirmeService = degerlendirmeService;
        this.soruService = soruService;
        this.objectMapper = objectMapper;
    }


    @PreAuthorize("hasAnyRole('Akademisyen','Idareci')")
    @GetMapping("/all")
    public List<Cevap> findAll() {
        return cevapService.findAll();
    }

    @PreAuthorize("hasAnyRole('Akademisyen','Idareci')")
    @GetMapping("/id/{id}")
    public List<Cevap> getCevap(@PathVariable Long id) {
        return cevapService.findAll();
    }

    @PreAuthorize("hasAnyRole('Ogrenci', 'Idareci')")
    @PostMapping("/add")
    public Cevap addCevap(@RequestBody Cevap theCevap) {
        theCevap.setId(null);
        Optional<Soru> soru = soruService.findById(theCevap.getSoru().getId());
        if (soru.isEmpty()) {
            return null;
        }
        Optional<Degerlendirme> degerlendirme = degerlendirmeService.findById(theCevap.getDegerlendirme().getId());
        if (degerlendirme.isEmpty()) {
            return null;
        }
        return cevapService.save(theCevap);
    }

    @PreAuthorize("hasAnyRole('Ogrenci', 'Idareci')")
    @PutMapping("/update/{id}")
    public Cevap updateCevap(@PathVariable Long id, @RequestBody Cevap theCevap) {
        var soru = soruService.findById(theCevap.getSoru().getId());
        if (soru.isEmpty()) {
            return null;
        }
        theCevap.setSoru(soru.get());

        var degerlendirmeOpt = degerlendirmeService.findById(theCevap.getDegerlendirme().getId());
        if (degerlendirmeOpt.isEmpty()) {
            return null;
        }
        theCevap.setDegerlendirme(degerlendirmeOpt.get());

        return cevapService.update(id, theCevap);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteCevap(@PathVariable Long id) {
        cevapService.deleteById(id);
        return "Cevap silindi - " + id;
    }

}