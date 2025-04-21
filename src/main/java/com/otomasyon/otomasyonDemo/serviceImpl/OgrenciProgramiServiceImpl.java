package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.OgrenciProgrami;
import com.otomasyon.otomasyonDemo.repository.OgrenciProgramiRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.OgrenciProgramiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OgrenciProgramiServiceImpl implements OgrenciProgramiService {
    private final OgrenciProgramiRepository ogrenciProgramiRepository;
    @Autowired
    public OgrenciProgramiServiceImpl(OgrenciProgramiRepository ogrenciProgramiRepository){
        this.ogrenciProgramiRepository=ogrenciProgramiRepository;
    }
    @Override
    public List<OgrenciProgrami> findAll() {
        return ogrenciProgramiRepository.findAll();
    }

    @Override
    public Optional<OgrenciProgrami> findById(Long id) {
        return ogrenciProgramiRepository.findById(id);
    }

    @Override
    public OgrenciProgrami save(OgrenciProgrami theOgrenciProgrami) {
        return ogrenciProgramiRepository.save(theOgrenciProgrami);
    }

    @Override
    public OgrenciProgrami update(Long id, OgrenciProgrami theOgrenciProgrami) {
        return ogrenciProgramiRepository.save(theOgrenciProgrami);
    }

    @Override
    public void deleteById(Long id) {
        if(ogrenciProgramiRepository.existsById(id)){
            ogrenciProgramiRepository.deleteById(id);
        }
    }
}
