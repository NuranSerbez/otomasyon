package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "degerlendirme")
public class Degerlendirme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ortalama")
    private int ortalama;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ogrenci")
    @JsonBackReference
    private User ogrenci;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "akademisyen")
    @JsonBackReference
    private User akademisyen;

}
