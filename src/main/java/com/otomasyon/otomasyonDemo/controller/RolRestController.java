package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.serviceInterface.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rol")
public class RolRestController {
    private RolService rolService;
    private ObjectMapper objectMapper;

    @Autowired

    public RolRestController(RolService rolService, ObjectMapper objectMapper) {
        this.rolService = rolService;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<Rol> findAll() {
        return rolService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen')")

    @GetMapping("/id/{id}")
    public Rol getRol(@PathVariable Long id) {
        return rolService.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol bulunamadı - " + id));
    }

    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public Rol addRol(@RequestBody Rol theRol) {
        theRol.setId(null);
        Rol dbRol = rolService.save(theRol);
        return dbRol;
    }

    @PreAuthorize("hasRole('Idareci')")
    @PutMapping("/update/{id}")
    public Rol updateRol(@PathVariable Long id, @RequestBody Rol theRol) {
        Rol rol = rolService.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol bulunamadı: " + id));
        rol.setRolTuru(theRol.getRolTuru());
        return rolService.save(rol);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteRol(@PathVariable Long id) {
        rolService.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol bulunamadı - " + id));
        rolService.deleteById(id);
        return "Rol silindi - " + id;
    }
}