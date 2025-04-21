package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Devamsizlik;
import com.otomasyon.otomasyonDemo.repository.DevamsizlikRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.DevamsizlikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevamsizlikServiceImpl implements DevamsizlikService {
    private final DevamsizlikRepository devamsizlikRepository;

    @Autowired
    public DevamsizlikServiceImpl(DevamsizlikRepository devamsizlikRepository) {
        this.devamsizlikRepository = devamsizlikRepository;
    }

    @Override
    public List<Devamsizlik> findAll() {
        return devamsizlikRepository.findAll();
    }

    @Override
    public Optional<Devamsizlik> findById(Long id) {
        return devamsizlikRepository.findById(id);
    }

    @Override
    public Devamsizlik save(Devamsizlik theDevamsizlik) {
        return devamsizlikRepository.save(theDevamsizlik);
    }

    @Override
    public Devamsizlik update(Long id, Devamsizlik theDevamsizlik) {
        return devamsizlikRepository.save(theDevamsizlik);
    }


    @Override
    public void deleteById(Long id) {
        if (devamsizlikRepository.existsById(id)) {
            devamsizlikRepository.deleteById(id);
        }
    }
}
