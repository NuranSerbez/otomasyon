package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Not;
import com.otomasyon.otomasyonDemo.repository.NotRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.NotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotServiceImpl implements NotService {
    private final NotRepository notRepository;
    @Autowired
    public NotServiceImpl(NotRepository notRepository){
        this.notRepository=notRepository;
    }
    @Override
    public List<Not> findAll() {
        return notRepository.findAll();
    }

    @Override
    public Optional<Not> findById(Long id) {
        return notRepository.findById(id);
    }

    @Override
    public Not save(Not theNot) {
        return notRepository.save(theNot);
    }

    @Override
    public Not update(Long id, Not theNot) {
        return notRepository.save(theNot);
    }

    @Override
    public void deleteById(Long id) {
        if(notRepository.existsById(id)){
            notRepository.deleteById(id);
        }
    }
}
