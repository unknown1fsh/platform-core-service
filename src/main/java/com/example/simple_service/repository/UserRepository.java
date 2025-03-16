package com.example.simple_service.repository;

import com.example.simple_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository, Spring Data JPA tarafından sağlanan JpaRepository arayüzünü genişletir.
 * Temel CRUD işlemlerinin yanı sıra ek metod imzalarını da tanımlar.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Belirtilen e-posta değerine sahip kullanıcıyı döndürür.
     * @param email Kullanıcının e-posta adresi
     * @return Varsa ilgili kullanıcıyı saran bir {@link Optional}, yoksa boş (empty)
     */
    Optional<User> findByEmail(String email);
}
