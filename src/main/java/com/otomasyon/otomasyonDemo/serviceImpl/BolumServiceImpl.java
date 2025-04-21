package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Bolum;
import com.otomasyon.otomasyonDemo.repository.BolumRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.BolumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BolumServiceImpl implements BolumService {

    private final BolumRepository bolumRepository;

    @Autowired
    public BolumServiceImpl(BolumRepository bolumRepository) {
        this.bolumRepository = bolumRepository;
    }

    @Override
    public List<Bolum> findAll() {
        return bolumRepository.findAll();
    }

    @Override
    public Optional<Bolum> findById(Long id) {
        return bolumRepository.findById(id);
    }

    @Override
    public Bolum save(Bolum theBolum) {
        return bolumRepository.save(theBolum);
    }

    @Override
    public Bolum update(Long id, Bolum theBolum) {
        return bolumRepository.save(theBolum);
    }


    @Override
    public BolumRepository deleteById(Long id) {
        if (bolumRepository.existsById(id)) {
            bolumRepository.deleteById(id);
            return bolumRepository;
        }
        return bolumRepository;
    }
}
