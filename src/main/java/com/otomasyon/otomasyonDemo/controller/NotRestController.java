package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Not;
import com.otomasyon.otomasyonDemo.repository.NotRepository;
import com.otomasyon.otomasyonDemo.repository.DersAtamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/not")
public class NotRestController {
    private NotRepository notRepository;
    private DersAtamaRepository dersAtamaRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public NotRestController(NotRepository notRepository, DersAtamaRepository dersAtamaRepository, ObjectMapper objectMapper) {
        this.notRepository = notRepository;
        this.dersAtamaRepository = dersAtamaRepository;
        this.objectMapper = objectMapper;
    }


    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<Not> findAll() {
        return notRepository.findAll();
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public Not getNot(@PathVariable Long id) {
        return notRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not bulunamadı - " + id));
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public Not addNot(@RequestBody Not theNot) {
        theNot.setId(null);
        Not dbNot = notRepository.save(theNot);
        return dbNot;
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public Not updateNot(@PathVariable Long id, @RequestBody Not theNot) {
        var not = notRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not bulunamadı: " + id));
        Long dersAtamaId = theNot.getDersAtama().getId();
        var dersAtama = dersAtamaRepository.findById(dersAtamaId)
                .orElseThrow(() -> new RuntimeException("Ders atama bulunamadı: " + dersAtamaId));
        not.setVize(theNot.getVize());
        not.setFinl(theNot.getFinl());
        not.setOrtalama(theNot.getOrtalama());
        not.setDersAtama(dersAtama);
        return notRepository.save(not);
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @DeleteMapping("/delete/{id}")
    public String deleteNot(@PathVariable Long id) {
        notRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not bulunamadı - " + id));
        notRepository.deleteById(id);
        return "Not silindi - " + id;
    }
}
