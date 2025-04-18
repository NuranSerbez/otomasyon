package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "program")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "program_turu")
    private String programTuru;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "program_bolum", // ara tablo adı
            joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "bolum_id")
    )
    @JsonManagedReference
    private List<Bolum> bolumler;
}
