package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sonuc")
public class Not {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vize")
    private int vize;

    @Column(name = "final")
    private int finl;

    @Column(name = "ortalama")
    private int ortalama;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ders")
    @JsonBackReference
    private DersAtama dersAtama;
}
