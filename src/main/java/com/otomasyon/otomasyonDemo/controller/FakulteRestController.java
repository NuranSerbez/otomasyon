package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Fakulte;
import com.otomasyon.otomasyonDemo.serviceInterface.FakulteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fakulte")
public class FakulteRestController {
    private FakulteService fakulteService;
    private ObjectMapper objectMapper;

    @Autowired

    public FakulteRestController(FakulteService fakulteService, ObjectMapper objectMapper) {
        this.fakulteService = fakulteService;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<Fakulte> findAll() {
        return fakulteService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public Fakulte getFakulte(@PathVariable Long id) {
        return fakulteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Fakülte bulunamadı - " + id));
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public Fakulte addFakulte(@RequestBody Fakulte theFakulte) {
        theFakulte.setId(null);
        Fakulte dbFakulte = fakulteService.save(theFakulte);
        return dbFakulte;
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public Fakulte updateFakulte(@RequestBody Fakulte theFakulte) {
        Long fakulteId = theFakulte.getId();
        var fakulte = fakulteService.findById(fakulteId)
                .orElseThrow(() -> new RuntimeException("Fakülte bulunamadı: " + fakulteId));
        fakulte.setFakulteAdi(theFakulte.getFakulteAdi());
        return fakulteService.save(fakulte);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteFakulte(@PathVariable Long id) {
        fakulteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Fakülte bulunamadı - " + id));
        fakulteService.deleteById(id);
        return "Fakülte silindi - " + id;
    }
}
