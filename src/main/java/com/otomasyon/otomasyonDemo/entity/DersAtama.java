package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ders_atama")
public class DersAtama {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ders")
    @JsonBackReference
    private Ders ders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ogrenci")
    @JsonBackReference
    private User ogrenci;

    @Column(name = "onay_durumu")
    private boolean onaydurum;
}
