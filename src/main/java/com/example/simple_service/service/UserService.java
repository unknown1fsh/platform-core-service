package com.example.simple_service.service;

import com.example.simple_service.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Kullanıcı işlemleri için iş mantığını tanımlayan servis sözleşmesi.
 */
public interface UserService {

    /**
     * Yeni bir kullanıcı kaydeder.
     *
     * @param user Kaydedilecek kullanıcı nesnesi
     * @return Kaydedilen kullanıcı
     */
    User saveUser(User user);

    /**
     * Tüm kullanıcıları döndürür.
     *
     * @return Tüm kullanıcıları içeren bir liste
     */
    List<User> getAllUsers();

    /**
     * ID değeri verilen kullanıcıyı döndürür.
     *
     * @param id Kullanıcının ID değeri
     * @return Varsa kullanıcı nesnesi, yoksa null
     */
    User getUserById(Long id);

    /**
     * Belirtilen ID değerine sahip kullanıcının bilgilerini günceller.
     *
     * @param id   Güncellenecek kullanıcının ID değeri
     * @param user Yeni kullanıcı bilgilerini içeren nesne
     * @return Güncellenmiş kullanıcı nesnesi, yoksa null
     */
    User updateUser(Long id, User user);

    /**
     * Belirtilen ID değerine sahip kullanıcıyı siler.
     *
     * @param id Silinecek kullanıcının ID değeri
     * @return Silme işlemi başarılıysa true, aksi halde false
     */
    boolean deleteUser(Long id);

    /**
     * Belirtilen e-posta değerine sahip kullanıcıyı döndürür.
     *
     * @param email Kullanıcının e-posta adresi
     * @return Varsa kullanıcıyı saran bir {@link Optional}, yoksa boş (empty)
     */
    Optional<User> getUserByEmail(String email);
}
