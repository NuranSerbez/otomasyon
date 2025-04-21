package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Cevap;
import com.otomasyon.otomasyonDemo.repository.CevapRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.CevapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CevapServiceImpl implements CevapService {

    private final CevapRepository cevapRepository;

    @Autowired
    public CevapServiceImpl(CevapRepository cevapRepository) {
        this.cevapRepository = cevapRepository;
    }

    @Override
    public List<Cevap> findAll() {
        return cevapRepository.findAll();
    }

    @Override
    public Optional<Cevap> findById(Long id) {
        return cevapRepository.findById(id);
    }

    @Override
    public Cevap save(Cevap theCevap) {
        return cevapRepository.save(theCevap);
    }

    @Override
    public Cevap update(Long id, Cevap theCevap) {
        return cevapRepository.save(theCevap);
    }


    @Override
    public void deleteById(Long id) {
        if (cevapRepository.existsById(id)) {
            cevapRepository.deleteById(id);
        }
    }
}
