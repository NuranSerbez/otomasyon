package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Ders;
import com.otomasyon.otomasyonDemo.repository.DersRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.DersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DersServiceImpl implements DersService {
    private final DersRepository dersRepository;

    @Autowired
    public DersServiceImpl(DersRepository dersRepository) {
        this.dersRepository = dersRepository;
    }

    @Override
    public List<Ders> findAll() {
        return dersRepository.findAll();
    }

    @Override
    public Optional<Ders> findById(Long id) {
        return dersRepository.findById(id);
    }

    @Override
    public Ders save(Ders theDers) {
        return dersRepository.save(theDers);
    }

    @Override
    public Ders update(Long id, Ders theDers) {
        return dersRepository.save(theDers);
    }

    @Override
    public void deleteById(Long id) {
        if (dersRepository.existsById(id)) {
            dersRepository.deleteById(id);
        }
    }
}
