package com.otomasyon.otomasyonDemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ogrenci_programi")
public class OgrenciProgrami {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ogrenci_programi_user",
            joinColumns = @JoinColumn(name = "ogrenci_programi_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> ogrenciler;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ogrenci_programi_program",
            joinColumns = @JoinColumn(name = "ogrenci_programi_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private List<Program> programlar;
}