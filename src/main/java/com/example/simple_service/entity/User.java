package com.example.simple_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity sınıfı: Sistemdeki kullanıcı bilgilerini temsil eder.
 */
@Entity
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * Kullanıcı tablosunun birincil anahtarı.
     * IDENTITY stratejisi, veritabanının kimlik yönetimine bırakır.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Kullanıcının isim-soyisim bilgisi.
     * Maksimum uzunluk ve boş olmama durumu gibi validasyonlar
     * eklemek isterseniz (örn. Bean Validation), ilgili anotasyonları ekleyebilirsiniz.
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * Kullanıcının e-posta adresi.
     * Tablo düzeyinde unique constraint tanımlanmıştır.
     */
    @Column(name = "email", nullable = false, length = 150)
    private String email;
}
