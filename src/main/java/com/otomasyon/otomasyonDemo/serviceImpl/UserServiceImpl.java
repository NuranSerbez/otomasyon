package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.repository.UserRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
   public UserServiceImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User theUser) {
        return userRepository.save(theUser);
    }

    @Override
    public User update(Long id, User theUser) {
        return userRepository.save(theUser);
    }

    @Override
    public void deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
}
