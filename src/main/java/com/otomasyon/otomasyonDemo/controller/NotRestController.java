package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Not;
import com.otomasyon.otomasyonDemo.serviceInterface.DersAtamaService;
import com.otomasyon.otomasyonDemo.serviceInterface.NotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/not")
public class NotRestController {
    private NotService notService;
    private DersAtamaService dersAtamaService;
    private ObjectMapper objectMapper;

    @Autowired
    public NotRestController(NotService notService, DersAtamaService dersAtamaService, ObjectMapper objectMapper) {
        this.notService = notService;
        this.dersAtamaService = dersAtamaService;
        this.objectMapper = objectMapper;
    }


    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<Not> findAll() {
        return notService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public Not getNot(@PathVariable Long id) {
        return notService.findById(id)
                .orElseThrow(() -> new RuntimeException("Not bulunamadı - " + id));
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public Not addNot(@RequestBody Not theNot) {
        theNot.setId(null);
        Not dbNot = notService.save(theNot);
        return dbNot;
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public Not updateNot(@PathVariable Long id, @RequestBody Not theNot) {
        var not = notService.findById(id)
                .orElseThrow(() -> new RuntimeException("Not bulunamadı: " + id));
        Long dersAtamaId = theNot.getDersAtama().getId();
        var dersAtama = dersAtamaService.findById(dersAtamaId)
                .orElseThrow(() -> new RuntimeException("Ders atama bulunamadı: " + dersAtamaId));
        not.setVize(theNot.getVize());
        not.setFinl(theNot.getFinl());
        not.setOrtalama(theNot.getOrtalama());
        not.setDersAtama(dersAtama);
        return notService.save(not);
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @DeleteMapping("/delete/{id}")
    public String deleteNot(@PathVariable Long id) {
        notService.findById(id)
                .orElseThrow(() -> new RuntimeException("Not bulunamadı - " + id));
        notService.deleteById(id);
        return "Not silindi - " + id;
    }
}
