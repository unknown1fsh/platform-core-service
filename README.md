# 🚀 Kullanıcı Yönetimi Servisi  
Basit ama profesyonel bir **Spring Boot 3 + Tailwind CSS** örneği  
_(Java tarafında RESTful API, ön yüzde vanilla JS)_

> “CRUD hiç bu kadar keyifli olmamıştı.” — **unknown1fsh**

---

## ✨ Neler Sunar?
| Katman | İçerik | Detay |
|--------|--------|-------|
| **Entity** | `User` | `id`, `ad`, `soyad`, `email` sütunları, e-posta için `UNIQUE` kısıtlaması |
| **Repository** | `UserRepository` | Spring Data JPA ile tek satırda CRUD + `findByEmail()` |
| **Service** | `UserService` / `UserServiceImpl` | İş mantığı, temiz DI, `Optional` kullanımı |
| **Controller** | `UserController` | REST uç noktaları (`/users`) ve CORS açılımı |
| **Frontend** | `index.html` | Tailwind CSS, karanlık mod, canlı arama & anlık tablo güncellemesi |
| **Konfigürasyon** | `application.properties` | **Hem PostgreSQL hem MySQL desteği** (profil tabanlı) |

---

## ⚙️ Mimari Şeması


Tarayıcı → fetch() → Spring Boot REST API → Service → Repository → (PostgreSQL | MySQL)


---

## 🏃‍♂️ Hızlı Başlangıç

### 1. Ön Koşullar  
* Java 17+ & Maven 3.9+  
* **Veritabanı:** PostgreSQL ≥ 15 **veya** MySQL 8+

### 2. Veritabanını Oluştur  
Aşağıdaki komut/skriptlerden sizin ortamınıza uygun olanı çalıştırın.  
> **Not ➜** Kullanıcı adı, parola ve port bilgilerini kendi ortam değişkenlerinizde veya Docker Compose dosyanızda tanımlayın; README’de hiçbir kişisel bilgi tutulmaz.

<details>
<summary><strong>PostgreSQL</strong> (SQL)</summary>


CREATE DATABASE user_service_db;

CREATE TABLE users (
    id      BIGSERIAL PRIMARY KEY,
    ad      VARCHAR(100) NOT NULL,
    soyad   VARCHAR(100) NOT NULL,
    email   VARCHAR(150) NOT NULL UNIQUE
);


</details>

<details>
<summary><strong>MySQL</strong> (SQL)</summary>


CREATE DATABASE user_service_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE user_service_db;

CREATE TABLE users (
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    ad      VARCHAR(100) NOT NULL,
    soyad   VARCHAR(100) NOT NULL,
    email   VARCHAR(150) NOT NULL UNIQUE
) ENGINE=InnoDB;


</details>

### 3. Projeyi Çalıştır


git clone <repo-url>
cd user-management-service

# Aktif profili seçerek (postgresql | mysql) örneğin:
mvn spring-boot:run -Dspring-boot.run.profiles=postgresql
# → Uygulama localhost:8080'de ayağa kalkar


### 4. Önyüzü Aç

# Yöntem 1 (VS Code Live Server)
code index.html

# Yöntem 2 (Küçük dahili servis)
python -m http.server 5500


Ardından tarayıcıdan `http://localhost:5500/index.html` adresine gidin.
Yeni eklediğiniz kayıtların en üstte listelendiğini (DESC sıralama) göreceksiniz.



## 🔌 API Rehberi

| Metot    | Uç Nokta              | Amaç                       |
| -------- | --------------------- | -------------------------- |
| `POST`   | `/users`              | Kullanıcı oluşturur        |
| `GET`    | `/users`              | Tüm kullanıcıları listeler |
| `GET`    | `/users/{id}`         | ID ile arar                |
| `GET`    | `/users/email?email=` | E-posta ile arar           |
| `PUT`    | `/users/{id}`         | Kullanıcı günceller        |
| `DELETE` | `/users/{id}`         | Kullanıcı siler            |

**Örnek cURL**


curl -X POST http://localhost:8080/users \
     -H "Content-Type: application/json" \
     -d '{"ad":"Ada","soyad":"Lovelace","email":"ada@lovelace.dev"}'
     
---

## 🌈 Ön Yüz Özellikleri

* **Tailwind CSS** ile cam efekti, responsive grid ve karanlık mod
* **Canlı tablo**: Yeni kayıtlar anında en üstte
* **Üçlü arama**: ID, Ad/Soyad veya E-posta ile filtreleme
* **Tek sayfa deneyimi**: Sayfa yenilenmeden CRUD işlemleri

---

## 🛣️ Yol Haritası

* [ ] JWT tabanlı kimlik doğrulama
* [ ] Swagger / OpenAPI dokümantasyonu
* [ ] Docker Compose (PostgreSQL + MySQL + Uygulama)
* [ ] React + Vite arayüz

Katkılarınızı bekliyoruz! ⭐️

---

## 🤝 Katkıda Bulunma

1. Fork ➜ Branch (`feature/xyz`)
2. Commit (anlamlı mesajlar)
3. Pull Request – açıklayıcı bir özetle birlikte

---

## 📝 Lisans

Free.

---

## 💬 İletişim

Projeyle ilgili sorularınız için **unknown1fsh**’e Issues veya Discussions üzerinden ulaşabilirsiniz.

> Kod sizin ✨, üretmek bizim görevimiz!
