package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.repository.UserRepository;
import com.otomasyon.otomasyonDemo.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private UserRepository userRepository;
    private RolRepository rolRepository;
    private ObjectMapper objectMapper;
    @Autowired
    public UserRestController(UserRepository userRepository, RolRepository rolRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.objectMapper = objectMapper;
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı - " + id));
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @PostMapping("/add")
    public User addUser(@RequestBody User theUser) {
        theUser.setId(null);
        User dbUser = userRepository.save(theUser);
        return dbUser;
    }
    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User theUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + id));
        user.setIsim(theUser.getIsim());
        user.setSoyisim(theUser.getSoyisim());
        user.setTckn(theUser.getTckn());
        user.setEmail(theUser.getEmail());
        user.setAdres(theUser.getAdres());
        user.setTelefon(theUser.getTelefon());
        user.setPassword(theUser.getPassword());
        user.setSifreGüncelligi(theUser.isSifreGüncelligi());
        Long rolId = theUser.getRol().getId();
        Rol rol = rolRepository.findById(rolId)
                .orElseThrow(() -> new RuntimeException("Rol bulunamadı: " + rolId));
        user.setRol(rol);
        return userRepository.save(user);
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunmadı - " + id));
        userRepository.deleteById(id);
        return "Kullanıcı silindi - " + id;
    }
}

