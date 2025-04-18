package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rol")
public class RolRestController {
    private RolRepository rolRepository;
    private ObjectMapper objectMapper;

    @Autowired

    public RolRestController(RolRepository rolRepository, ObjectMapper objectMapper) {
        this.rolRepository = rolRepository;
        this.objectMapper = objectMapper;
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen')")

    @GetMapping("/id/{id}")
    public Rol getRol(@PathVariable Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol bulunamadı - " + id));
    }
    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public Rol addRol(@RequestBody Rol theRol) {
        theRol.setId(null);
        Rol dbRol = rolRepository.save(theRol);
        return dbRol;
    }
    @PreAuthorize("hasRole('Idareci')")
    @PutMapping("/update/{id}")
    public Rol updateRol(@PathVariable Long id, @RequestBody Rol theRol) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol bulunamadı: " + id));
        rol.setRolTuru(theRol.getRolTuru());
        return rolRepository.save(rol);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteRol(@PathVariable Long id) {
        rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol bulunamadı - " + id));
        rolRepository.deleteById(id);
        return "Rol silindi - " + id;
    }
}