package com.GTRows.springbootuserservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GTRows.springbootuserservice.User;
import com.GTRows.springbootuserservice.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Tüm kullanıcıları getir
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ID'ye göre kullanıcı getir
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Yeni kullanıcı oluştur veya varolanı güncelle
    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    // Kullanıcı sil
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // E-posta ile kullanıcı arama (Örnek: Özel bir sorgu metodu eklemek isterseniz)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
