package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "isim")
    private String isim;

    @Column(name = "soyisim")
    private String soyisim;

    @Column(name = "TCKN", nullable = false, unique = true)
    private int tckn;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "adres")
    private String adres;

    @Column(name = "telefon")
    private int telefon;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rol")
    @JsonBackReference
    private Rol rol;

    @Column(name = "sifre_guncel_mi")
    private boolean sifreGüncelligi;
}
