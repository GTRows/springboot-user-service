package com.GTRows.springbootuserservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.GTRows.springbootuserservice.User;
import com.GTRows.springbootuserservice.UserService;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // Tüm kullanıcıları listele
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Belirli bir ID'ye sahip kullanıcıyı getir
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Yeni bir kullanıcı ekle
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveOrUpdateUser(user);
    }

    // Belirli bir kullanıcıyı güncelle
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User existingUser = userService.getUserById(id);
        if (existingUser != null) {
            userDetails.setId(id);
            return ResponseEntity.ok(userService.saveOrUpdateUser(userDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Belirli bir kullanıcıyı sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User existingUser = userService.getUserById(id);
        if (existingUser != null) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
