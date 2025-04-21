package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.serviceInterface.RolService;
import com.otomasyon.otomasyonDemo.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private UserService userService;
    private RolService rolService;
    private ObjectMapper objectMapper;

    @Autowired
    public UserRestController(UserService userService, RolService rolService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.rolService = rolService;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı - " + id));
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @PostMapping("/add")
    public User addUser(@RequestBody User theUser) {
        theUser.setId(null);
        User dbUser = userService.save(theUser);
        return dbUser;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User theUser) {
        User user = userService.findById(id)
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
        Optional<Rol> rol = rolService.findById(rolId);
        if (rol.isPresent()) {
            user.setRol(rol.get());
        }
        return userService.save(user);
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunmadı - " + id));
        userService.deleteById(id);
        return "Kullanıcı silindi - " + id;
    }
}

