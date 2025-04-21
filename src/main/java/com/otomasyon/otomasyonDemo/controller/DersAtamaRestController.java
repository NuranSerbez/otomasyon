package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.DersAtama;
import com.otomasyon.otomasyonDemo.serviceInterface.DersAtamaService;
import com.otomasyon.otomasyonDemo.serviceInterface.DersService;
import com.otomasyon.otomasyonDemo.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ders_atama")
public class DersAtamaRestController {
    private DersAtamaService dersAtamaService;
    private UserService userService;
    private DersService dersService;
    private ObjectMapper objectMapper;

    @Autowired
    public DersAtamaRestController(DersAtamaService dersAtamaService, UserService userService, DersService dersService, ObjectMapper objectMapper) {
        this.dersAtamaService = dersAtamaService;
        this.userService = userService;
        this.dersService = dersService;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @GetMapping("/all")
    public List<DersAtama> findAll() {
        return dersAtamaService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @GetMapping("/id/{id}")
    public DersAtama getDersAtama(@PathVariable Long id) {
        return dersAtamaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Ders atama bulunamadı - " + id));
    }

    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public DersAtama addDersAtama(@RequestBody DersAtama theDersAtama) {
        theDersAtama.setId(null);
        DersAtama dbDersAtama = dersAtamaService.save(theDersAtama);
        return dbDersAtama;
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public DersAtama updateDersAtama(@PathVariable Long id, @RequestBody DersAtama theDersAtama) {
        var dersAtama = dersAtamaService.findById(id)
                .orElseThrow(() -> new RuntimeException("DersAtama bulunamadı: " + id));
        Long dersId = theDersAtama.getDers().getId();
        var ders = dersService.findById(dersId)
                .orElseThrow(() -> new RuntimeException("Ders bulunamadı: " + dersId));
        Long ogrenciId = theDersAtama.getOgrenci().getId();
        var ogrenci = userService.findById(ogrenciId)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı: " + ogrenciId));
        dersAtama.setDers(ders);
        dersAtama.setOgrenci(ogrenci);
        dersAtama.setOnaydurum(theDersAtama.isOnaydurum());
        return dersAtamaService.save(dersAtama);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteDersAtama(@PathVariable Long id) {
        dersAtamaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Ders atama bulunamadı - " + id));
        dersAtamaService.deleteById(id);
        return "Ders atama silindi - " + id;
    }
}
