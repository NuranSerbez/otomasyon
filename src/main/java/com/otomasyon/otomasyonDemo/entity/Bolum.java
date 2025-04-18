package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bolum")
public class Bolum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bolum_adi")
    private String bolumAdi;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fakulte")
    @JsonBackReference
    private Fakulte fakulte;
}
