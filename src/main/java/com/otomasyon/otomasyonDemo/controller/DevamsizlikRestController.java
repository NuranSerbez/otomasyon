package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Devamsizlik;
import com.otomasyon.otomasyonDemo.repository.DevamsizlikRepository;
import com.otomasyon.otomasyonDemo.repository.DersAtamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devamsizlik")
public class DevamsizlikRestController {
    private DevamsizlikRepository devamsizlikRepository;
    private DersAtamaRepository dersAtamaRepository;
    private ObjectMapper objectMapper;
    @Autowired
    public DevamsizlikRestController(DevamsizlikRepository devamsizlikRepository, DersAtamaRepository dersAtamaRepository, ObjectMapper objectMapper) {
        this.devamsizlikRepository = devamsizlikRepository;
        this.dersAtamaRepository = dersAtamaRepository;
        this.objectMapper = objectMapper;
    }


    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<Devamsizlik> findAll() {
        return devamsizlikRepository.findAll();
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public Devamsizlik getDevamsizlik(@PathVariable Long id) {
        return devamsizlikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Devamsızlık bulunamadı - " + id));
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public Devamsizlik addDevamsizlik(@RequestBody Devamsizlik theDevamsizlik) {
        theDevamsizlik.setId(null);
        Devamsizlik dbDevamsizlik = devamsizlikRepository.save(theDevamsizlik);
        return dbDevamsizlik;
    }
    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public Devamsizlik updateDevamsizlik(@RequestBody Devamsizlik theDevamsizlik) {
        Long devamsizlikId = theDevamsizlik.getId();
        var devamsizlik = devamsizlikRepository.findById(devamsizlikId)
                .orElseThrow(() -> new RuntimeException("Devamsızlık bulunamadı: " + devamsizlikId));
        Long dersAtamaId = theDevamsizlik.getDersAtama().getId();
        var dersAtama = dersAtamaRepository.findById(dersAtamaId)
                .orElseThrow(() -> new RuntimeException("DersAtama bulunamadı: " + dersAtamaId));
        devamsizlik.setToplamSaat(theDevamsizlik.getToplamSaat());
        devamsizlik.setDersAtama(dersAtama);
        return devamsizlikRepository.save(devamsizlik);
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @DeleteMapping("/{id}")
    public String deleteDevamsizlik(@PathVariable Long id) {
        devamsizlikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Devamsızlık bulunamadı - " + id));
        devamsizlikRepository.deleteById(id);
        return "Devamsızlık silindi - " + id;
    }
}
