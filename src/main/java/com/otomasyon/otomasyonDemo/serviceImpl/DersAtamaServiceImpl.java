package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.DersAtama;
import com.otomasyon.otomasyonDemo.repository.DersAtamaRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.DersAtamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DersAtamaServiceImpl implements DersAtamaService {
    private final DersAtamaRepository dersAtamaRepository;

    @Autowired
    public DersAtamaServiceImpl(DersAtamaRepository dersAtamaRepository) {
        this.dersAtamaRepository = dersAtamaRepository;
    }

    @Override
    public List<DersAtama> findAll() {
        return dersAtamaRepository.findAll();
    }

    @Override
    public Optional<DersAtama> findById(Long id) {
        return dersAtamaRepository.findById(id);
    }

    @Override
    public DersAtama save(DersAtama theDersAtama) {
        return dersAtamaRepository.save(theDersAtama);
    }

    @Override
    public DersAtama update(Long id, DersAtama theDersAtama) {
        return dersAtamaRepository.save(theDersAtama);
    }

    @Override
    public void deleteById(Long id) {
        if (dersAtamaRepository.existsById(id)) {
            dersAtamaRepository.deleteById(id);
        }
    }
}
