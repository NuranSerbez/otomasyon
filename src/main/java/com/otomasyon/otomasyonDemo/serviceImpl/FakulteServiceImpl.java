package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Fakulte;
import com.otomasyon.otomasyonDemo.repository.FakulteRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.FakulteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FakulteServiceImpl implements FakulteService {

    private final FakulteRepository fakulteRepository;

    @Autowired
    public FakulteServiceImpl(FakulteRepository fakulteRepository) {
        this.fakulteRepository = fakulteRepository;
    }

    @Override
    public List<Fakulte> findAll() {
        return fakulteRepository.findAll();
    }

    @Override
    public Optional<Fakulte> findById(Long id) {
        return fakulteRepository.findById(id);
    }

    @Override
    public Fakulte save(Fakulte theFakulte) {
        return fakulteRepository.save(theFakulte);
    }

    @Override
    public Fakulte update(Long id, Fakulte theFakulte) {
        return fakulteRepository.save(theFakulte);
    }

    @Override
    public void deleteById(Long id) {
        if (fakulteRepository.existsById(id)) {
            fakulteRepository.deleteById(id);
        }
    }
}
