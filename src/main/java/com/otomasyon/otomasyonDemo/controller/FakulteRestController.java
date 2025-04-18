package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Fakulte;
import com.otomasyon.otomasyonDemo.repository.FakulteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fakulte")
public class FakulteRestController {
    private FakulteRepository fakulteRepository;
    private ObjectMapper objectMapper;

    @Autowired

    public FakulteRestController(FakulteRepository fakulteRepository, ObjectMapper objectMapper) {
        this.fakulteRepository = fakulteRepository;
        this.objectMapper = objectMapper;
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<Fakulte> findAll() {
        return fakulteRepository.findAll();
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public Fakulte getFakulte(@PathVariable Long id) {
        return fakulteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fakülte bulunamadı - " + id));
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public Fakulte addFakulte(@RequestBody Fakulte theFakulte) {
        theFakulte.setId(null);
        Fakulte dbFakulte = fakulteRepository.save(theFakulte);
        return dbFakulte;
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public Fakulte updateFakulte(@RequestBody Fakulte theFakulte) {
        Long fakulteId = theFakulte.getId();
        var fakulte = fakulteRepository.findById(fakulteId)
                .orElseThrow(() -> new RuntimeException("Fakülte bulunamadı: " + fakulteId));
        fakulte.setFakulteAdi(theFakulte.getFakulteAdi());
        return fakulteRepository.save(fakulte);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteFakulte(@PathVariable Long id) {
        fakulteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fakülte bulunamadı - " + id));
        fakulteRepository.deleteById(id);
        return "Fakülte silindi - " + id;
    }
}
