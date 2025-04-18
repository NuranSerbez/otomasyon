package com.otomasyon.otomasyonDemo.repository;

import com.otomasyon.otomasyonDemo.entity.Soru;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoruRepository extends JpaRepository<Soru, Long> {
}
