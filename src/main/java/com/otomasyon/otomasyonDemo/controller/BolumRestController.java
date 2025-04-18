package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Bolum;
import com.otomasyon.otomasyonDemo.repository.BolumRepository;
import com.otomasyon.otomasyonDemo.repository.FakulteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/bolum")
public class BolumRestController {
    private final BolumRepository bolumRepository;
    private final FakulteRepository fakulteRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public BolumRestController(BolumRepository bolumRepository, FakulteRepository fakulteRepository, ObjectMapper objectMapper) {
        this.bolumRepository = bolumRepository;
        this.fakulteRepository = fakulteRepository;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/all")
    public List<Bolum> findAll() {
        return bolumRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public Bolum getBolum(@PathVariable Long id) {
        return bolumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bölüm bulunamadı - " + id));
    }

    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public Bolum addBolum(@RequestBody Bolum theBolum) {
        theBolum.setId(null);
        Long fakulteId = theBolum.getFakulte().getId();
        var fakulte = fakulteRepository.findById(fakulteId)
                .orElseThrow(() -> new RuntimeException("Fakülte bulunamadı: " + fakulteId));
        theBolum.setFakulte(fakulte);
        Bolum dbBolum = bolumRepository.save(theBolum);
        return dbBolum;
    }

    @PreAuthorize("hasRole('Idareci')")
    @PutMapping("/update/{id}")
    public Bolum updateBolum(@PathVariable Long id, @RequestBody Bolum theBolum) {
        var bolum = bolumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bölüm bulunamadı: " + id));
        Long fakulteId = theBolum.getFakulte().getId();
        var fakulte = fakulteRepository.findById(fakulteId)
                .orElseThrow(() -> new RuntimeException("Fakülte bulunamadı: " + fakulteId));
        bolum.setBolumAdi(theBolum.getBolumAdi());
        bolum.setFakulte(fakulte);
        return bolumRepository.save(bolum);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteBolum(@PathVariable Long id) {
        bolumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bölüm bulunamadı - " + id));
        bolumRepository.deleteById(id);
        return "Bölüm silindi - " + id;
    }
}
