package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Bolum;
import com.otomasyon.otomasyonDemo.entity.Fakulte;
import com.otomasyon.otomasyonDemo.serviceInterface.BolumService;
import com.otomasyon.otomasyonDemo.serviceInterface.FakulteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bolum")
public class BolumRestController {

    private final BolumService bolumService;
    private final FakulteService fakulteService;
    private final ObjectMapper objectMapper;

    @Autowired
    public BolumRestController(BolumService bolumService, FakulteService fakulteService, ObjectMapper objectMapper) {
        this.bolumService = bolumService;
        this.fakulteService = fakulteService;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/all")
    public List<Bolum> findAll() {
        return bolumService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/id/{id}")
    public Optional<Bolum> getBolum(@PathVariable Long id) {
        return bolumService.findById(id);
    }

    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public Bolum addBolum(@RequestBody Bolum theBolum) {
        theBolum.setId(null);
        Optional<Fakulte> fakulte = fakulteService.findById(theBolum.getFakulte().getId());
        if (fakulte.isEmpty()) {
            return null;
        }
        theBolum.setFakulte(fakulte);
        return bolumService.save(theBolum);
    }

    @PreAuthorize("hasRole('Idareci')")
    @PutMapping("/update/{id}")
    public Bolum updateBolum(@PathVariable Long id, @RequestBody Bolum theBolum) {
        var fakulte = fakulteService.findById(theBolum.getFakulte().getId());
        if (fakulte.isEmpty()) {
            return null;
        }
        theBolum.setFakulte(fakulte);
        return bolumService.update(id, theBolum);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteBolum(@PathVariable Long id) {
        bolumService.deleteById(id);
        return "Bölüm silindi - " + id;
    }
}
