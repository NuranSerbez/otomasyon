package com.otomasyon.otomasyonDemo.repository;

import com.otomasyon.otomasyonDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}