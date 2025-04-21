package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.repository.RolRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {
    private RolRepository rolRepository;
    @Autowired
    public RolServiceImpl(RolRepository rolRepository){
        this.rolRepository=rolRepository;
    }
    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> findById(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public Rol save(Rol theRol) {
        return rolRepository.save(theRol);
    }

    @Override
    public Rol update(Long id, Rol theRol) {
        return rolRepository.save(theRol);
    }

    @Override
    public void deleteById(Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
        }
    }
}
