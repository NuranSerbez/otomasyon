package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Degerlendirme;
import com.otomasyon.otomasyonDemo.repository.DegerlendirmeRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.DegerlendirmeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DegerlendirmeServiceImpl implements DegerlendirmeService {

private final DegerlendirmeRepository degerlendirmeRepository;

    public DegerlendirmeServiceImpl(DegerlendirmeRepository degerlendirmeRepository) {
        this.degerlendirmeRepository = degerlendirmeRepository;
    }

    @Override
    public List<Degerlendirme> findAll() {
        return degerlendirmeRepository.findAll();
    }

    @Override
    public Optional<Degerlendirme> findById(Long id) {
        return degerlendirmeRepository.findById(id);
    }


    @Override
    public Degerlendirme save(Degerlendirme theDegerlendirme) {
        return degerlendirmeRepository.save(theDegerlendirme);
    }

    @Override
    public Degerlendirme update(Long id, Degerlendirme theDegerlendirme) {
        return degerlendirmeRepository.save(theDegerlendirme);
    }

    @Override
    public void deleteById(Long id) {
        if (degerlendirmeRepository.existsById(id)) {
            degerlendirmeRepository.deleteById(id);
        }
    }
}
