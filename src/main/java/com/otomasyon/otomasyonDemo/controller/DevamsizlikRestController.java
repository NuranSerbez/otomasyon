package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Devamsizlik;
import com.otomasyon.otomasyonDemo.serviceInterface.DersAtamaService;
import com.otomasyon.otomasyonDemo.serviceInterface.DevamsizlikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devamsizlik")
public class DevamsizlikRestController {
    private DevamsizlikService devamsizlikService;
    private DersAtamaService dersAtamaService;
    private ObjectMapper objectMapper;

    @Autowired
    public DevamsizlikRestController(DevamsizlikService devamsizlikService, DersAtamaService dersAtamaService, ObjectMapper objectMapper) {
        this.devamsizlikService = devamsizlikService;
        this.dersAtamaService = dersAtamaService;
        this.objectMapper = objectMapper;
    }


    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<Devamsizlik> findAll() {
        return devamsizlikService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public Devamsizlik getDevamsizlik(@PathVariable Long id) {
        return devamsizlikService.findById(id)
                .orElseThrow(() -> new RuntimeException("Devamsızlık bulunamadı - " + id));
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public Devamsizlik addDevamsizlik(@RequestBody Devamsizlik theDevamsizlik) {
        theDevamsizlik.setId(null);
        Devamsizlik dbDevamsizlik = devamsizlikService.save(theDevamsizlik);
        return dbDevamsizlik;
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public Devamsizlik updateDevamsizlik(@RequestBody Devamsizlik theDevamsizlik) {
        Long devamsizlikId = theDevamsizlik.getId();
        var devamsizlik = devamsizlikService.findById(devamsizlikId)
                .orElseThrow(() -> new RuntimeException("Devamsızlık bulunamadı: " + devamsizlikId));
        Long dersAtamaId = theDevamsizlik.getDersAtama().getId();
        var dersAtama = dersAtamaService.findById(dersAtamaId)
                .orElseThrow(() -> new RuntimeException("DersAtama bulunamadı: " + dersAtamaId));
        devamsizlik.setToplamSaat(theDevamsizlik.getToplamSaat());
        devamsizlik.setDersAtama(dersAtama);
        return devamsizlikService.save(devamsizlik);
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @DeleteMapping("/{id}")
    public String deleteDevamsizlik(@PathVariable Long id) {
        devamsizlikService.findById(id)
                .orElseThrow(() -> new RuntimeException("Devamsızlık bulunamadı - " + id));
        devamsizlikService.deleteById(id);
        return "Devamsızlık silindi - " + id;
    }
}
