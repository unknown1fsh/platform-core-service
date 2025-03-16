package com.example.simple_service.controller;

import com.example.simple_service.entity.User;
import com.example.simple_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller katmanı: Kullanıcı işlemleriyle ilgili tüm uç noktaları yönetir.
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    /**
     * Constructor Injection: Daha test edilebilir ve güvenli bir yaklaşımdır.
     *
     * @param userService Kullanıcı servisi
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Yeni bir kullanıcı oluşturur.
     *
     * @param user Oluşturulacak kullanıcı verisi
     * @return Oluşturulan kullanıcının bilgileri (201 Created)
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    /**
     * Sistemdeki tüm kullanıcıları döndürür.
     *
     * @return Kullanıcıların listesi (200 OK)
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Belirtilen ID'ye sahip kullanıcıyı getirir.
     *
     * @param id Kullanıcı ID değeri
     * @return Kullanıcı bilgileri (200 OK) veya bulunamadıysa (404 Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = Optional.ofNullable(userService.getUserById(id));
        return userOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Belirtilen ID'ye sahip kullanıcı bilgisini günceller.
     *
     * @param id   Güncellenecek kullanıcının ID değeri
     * @param user Yeni kullanıcı verisi
     * @return Güncellenmiş kullanıcı bilgileri (200 OK) veya bulunamadıysa (404 Not Found)
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> updatedUser = Optional.ofNullable(userService.updateUser(id, user));
        return updatedUser
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Belirtilen ID'ye sahip kullanıcıyı siler.
     *
     * @param id Silinecek kullanıcının ID değeri
     * @return İçerik yok (204 No Content) veya bulunamadıysa (404 Not Found)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Belirtilen e-posta adresine sahip kullanıcıyı döndürür.
     *
     * @param email Kullanıcı e-posta değeri
     * @return Kullanıcı bilgisi (200 OK) veya boş ise (200 OK içinde boş değer)
     */
    @GetMapping("/email")
    public ResponseEntity<Optional<User>> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
