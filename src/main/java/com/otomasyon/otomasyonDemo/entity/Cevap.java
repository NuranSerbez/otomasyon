package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cevap")
public class Cevap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "degerlendirme")
    @JsonBackReference
    private Degerlendirme degerlendirme;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "soru")
    @JsonBackReference
    private Soru soru;

    @Column(name = "puan")
    private int puan;
}