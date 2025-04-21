package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Soru;
import com.otomasyon.otomasyonDemo.repository.SoruRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.SoruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoruServiceImpl implements SoruService {

    private final SoruRepository soruRepository;
    @Autowired
    public SoruServiceImpl(SoruRepository soruRepository){
    this.soruRepository= soruRepository;
    }

    @Override
    public List<Soru> findAll() {
        return soruRepository.findAll();
    }

    @Override
    public Optional<Soru> findById(Long id) {
        return soruRepository.findById(id);
    }

    @Override
    public Soru save(Soru theSoru) {
        return soruRepository.save(theSoru);
    }

    @Override
    public Soru update(Long id, Soru theSoru) {
        return soruRepository.save(theSoru);
    }

    @Override
    public void deleteById(Long id) {
        if (soruRepository.existsById(id)) {
            soruRepository.deleteById(id);
    }
}
}
