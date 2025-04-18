package com.otomasyon.otomasyonDemo.repository;

import com.otomasyon.otomasyonDemo.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
}
