## Platform Core Service

Kurumsal uygulamalar için hazırlanmış, kullanıcı ve parametre yönetimi odaklı **modern bir yönetim paneli (admin UI) ve REST servis** projesidir.  
Spring Boot, Spring Security, Spring Data JPA, Thymeleaf ve MySQL üzerinde çalışır.

---

## İçerik

- [Genel Mimari](#genel-mimari)
- [Özellikler](#özellikler)
- [Teknolojiler](#teknolojiler)
- [Kurulum](#kurulum)
  - [Ön Gereksinimler](#ön-gereksinimler)
  - [Veritabanı](#veritabanı)
  - [Application Properties](#application-properties)
  - [Uygulamayı Çalıştırma](#uygulamayı-çalıştırma)
- [Kullanım](#kullanım)
  - [Giriş (Login)](#giriş-login)
  - [Dashboard](#dashboard)
  - [Kullanıcı Yönetimi](#kullanıcı- yönetimi)
  - [Parametre Yönetimi](#parametre- yönetimi)
  - [Rol Yönetimi](#rol-yönetimi)
  - [Audit Log](#audit-log)
- [DB Şeması ve Migration’lar](#db-şeması-ve-migrationlar)
- [Güvenlik ve Roller](#güvenlik-ve-roller)
- [Geliştirme Notları](#geliştirme-notları)

---

## Genel Mimari

Proje **çok katmanlı** bir yapıda tasarlanmıştır:

- `controller`  
  - REST controller’lar (`/api/**`)  
  - UI controller’lar (Thymeleaf sayfaları, `/users`, `/parameters`, `/roles`, `/audit-logs` vs.)
- `service`  
  - İş kuralları, validasyonlar ve transaction yönetimi
- `repository`  
  - Spring Data JPA ile MySQL erişimi
- `entity` / `dto` / `mapper`  
  - JPA entity’leri, DTO’lar ve MapStruct ile mapping
- `templates` / `static`  
  - Thymeleaf template’leri, CSS/JS ve modern admin panel UI tasarımı

---

## Özellikler

- **Modern Dashboard UI**
  - Sol tarafta sabit sidebar, üstte kullanıcı bilgili header, altta footer
  - Dark-theme, blur ve gradient tabanlı, SaaS tarzı görünüm

- **Kullanıcı Yönetimi**
  - Kullanıcı listesi, filtreleme, sayfalama
  - Kullanıcı ekleme/güncelleme
  - Kullanıcı durum alanı (`ACTIVE`, `INACTIVE`, `BLOCKED`)

- **Parametre Yönetimi**
  - Kategori / kod kırılımında parametreler
  - Sistem ayarları, limitler ve feature flag’ler için uygun yapı

- **Rol Yönetimi**
  - Rol listesi ve CRUD
  - Kullanıcıların rollerle ilişkilendirilebilmesi için domain modeli (Role, UserRole)

- **Audit Log**
  - Kullanıcı aksiyonlarını ileride loglayabilmek için altyapı
  - Örnek log kayıtlarını listeleyen modern bir tablo ekranı

- **Mock / Demo Veriler**
  - Flyway migration’ları ile demo kullanıcılar, parametreler ve audit log kayıtları

---

## Teknolojiler

- **Backend**
  - Java 17
  - Spring Boot 3.3.x
  - Spring MVC
  - Spring Security 6
  - Spring Data JPA & Hibernate
  - Flyway (DB migration)

- **Veritabanı**
  - MySQL 8.x

- **UI / View Katmanı**
  - Thymeleaf
  - HTML5, modern CSS (custom design system)
  - Vanilla JS (minimal)

---

## Kurulum

### Ön Gereksinimler

- Java 17 (JDK)
- Maven 3.9+ (veya projedeki `mvnw` wrapper)
- MySQL 8.x

### Veritabanı

Proje varsayılan olarak aşağıdaki veritabanını kullanır:

- Veritabanı adı: `platform_core_demo`

MySQL’de bu veritabanını manuel oluşturmak istersen:

```sql
CREATE DATABASE platform_core_demo CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Ancak `application.properties`’te `createDatabaseIfNotExist=true` kullanıldığı için, MySQL kullanıcı hesabının gerekli yetkileri varsa veritabanı otomatik de oluşabilir.

### Application Properties

Temel ayarlar `src/main/resources/application.properties` içinde:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/platform_core_demo?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=12345

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
spring.flyway.locations=classpath:db/migration
spring.flyway.validate-on-migrate=false

spring.security.user.name=admin
spring.security.user.password=Admin123!
```

Gerekirse `username` / `password` değerlerini kendi MySQL kurulumuna göre güncellemelisin.

### Uygulamayı Çalıştırma

Proje kök dizininde (Maven wrapper kullanarak):

```bash
cd platform-core-service
./mvnw spring-boot:run       # Linux/Mac

mvnw.cmd spring-boot:run     # Windows (PowerShell / CMD)
```

Uygulama varsayılan olarak şu adreste çalışır:

- `http://localhost:8080`

---

## Kullanım

### Giriş (Login)

- URL: `http://localhost:8080/login`
- Demo kullanıcı bilgileri:
  - Kullanıcı adı: `admin`
  - Şifre: `Admin123!`

Login sayfası:

- Koyu temalı, gradient arka planlı; ortada markalı bir login kartı.
- Hatalı girişte kırmızı uyarı, başarılı logout sonrası yeşil bilgilendirme mesajı.

### Dashboard

Giriş sonrası ana sayfa (`/`):

- Servis sağlık bilgisi (health endpoint sonucu)
- Kullanıcı ve parametre ekranlarına hızlı erişim kartları
- Tasarım olarak modern SaaS admin panellerine benzer bir görünüm sunar.

### Kullanıcı Yönetimi

- URL: `/users`
- Özellikler:
  - Kullanıcı listesini tablo halinde görüntüleme
  - Kullanıcı adına ve duruma göre filtreleme
  - Sayfalama (pagination)
  - Yeni kullanıcı ekleme (`/users/new`)
  - Kullanıcı düzenleme (`/users/{id}/edit`)

### Parametre Yönetimi

- URL: `/parameters`
- Özellikler:
  - Kategori ve kod bazlı filtreleme
  - Parametre listesini tablo halinde görüntüleme
  - Yeni parametre ekleme (`/parameters/new`)
  - Parametre düzenleme (`/parameters/{id}/edit`)
  - Parametreler sistem davranışını etkileyen ayar ve feature flag’ler olarak tasarlanmıştır.

### Rol Yönetimi

- URL: `/roles`
- Entity’ler:
  - `Role` (ör. `ROLE_ADMIN`, `ROLE_OPERATOR`, `ROLE_VIEWER`)
  - `UserRole` (kullanıcı <-> rol ilişkisi)
- Özellikler:
  - Rol listesi, açıklamalarıyla birlikte
  - Yeni rol ekleme (`/roles/new`)
  - Rol düzenleme (`/roles/{id}/edit`)

Bu yapı, gelecekte kullanıcı-rol eşleştirmesi ve rol bazlı yetkilendirmeyi daha da detaylandırmak için temel sağlar.

### Audit Log

- URL: `/audit-logs`
- Entity: `AuditLog`
- Özellikler:
  - Kullanıcı adına göre filtrelenebilir log listesi
  - Sütunlar: zaman, kullanıcı, aksiyon, kaynak, detay, IP
  - Şu an örnek seed veriler üzerinden çalışır; ileride gerçek aksiyonlara entegre edilebilir.

---

## DB Şeması ve Migration’lar

Flyway migration dosyaları `src/main/resources/db/migration` dizininde bulunur:

- `V1__baseline.sql`  
  - `user_account` ve `parameter` tablolarının temel şemasını oluşturur.

- `V2__cleanup.sql`  
  - İlk kurulumlarda sorun yaratmaması için **NO-OP** (iş yapmayan) hale getirilmiştir.  
  - Gerekirse manuel bakım için örnek ALTER/INDEX komutları yorum satırı olarak bırakılmıştır.

- `V3__roles_and_audit_tables.sql`  
  - `role`, `user_role` ve `audit_log` tablolarını oluşturur.

- `V4__seed_users_parameters_audit.sql`  
  - Demo roller (`ROLE_ADMIN`, `ROLE_OPERATOR`, `ROLE_VIEWER`)  
  - Demo kullanıcılar (`admin`, `operator1`, `viewer1`, `demo_user`)  
  - Örnek parametreler (`GENERAL`, `SECURITY`, `FEATURE`, `LIMITS` kategorileri)  
  - Örnek audit log kayıtları

Migration’lar ilk açılışta sıralı olarak çalıştırılır. Bir hata durumunda Flyway roll-back yapılmasını önerir; geliştirme ortamında genellikle ilgili veritabanını düşürüp (drop) tekrar oluşturmak yeterlidir.

---

## Güvenlik ve Roller

`SecurityConfig` üzerinde:

- Login sayfası: `/login`
- Başarılı login sonrası: `/` (dashboard)
- Statik varlıklar (`/css/**`, `/js/**`, `/images/**`) herkese açık.
- `/login`, `/actuator/**` herkese açık; diğer bütün endpoint’ler authentication gerektirir.
- In-memory admin kullanıcısı `spring.security.user.*` ayarları üzerinden tanımlanmıştır.

Domain tarafında ise:

- Rol yapısı (`Role`, `UserRole`) hazır durumdadır.
- İleride `UserAccount` ile entegre edilerek, Spring Security tarafında `GrantedAuthority` üretmek üzere genişletilebilir.

---

## Geliştirme Notları

- **Kod Üretimi / MapStruct**  
  - DTO <-> Entity dönüşümleri için MapStruct kullanılır (`mapper` paketindeki interface’ler).

- **UI Tasarımı**  
  - Tüm modern görünüm `static/css/app.css` içinde custom bir design system olarak tanımlanmıştır.
  - Yeni sayfa veya bileşen eklerken mevcut sınıfları (ör. `.page-header`, `.form-card`, `.btn-primary`) tekrar kullanmak tutarlılığı artırır.

- **Logging**  
  - `logging.level.com.sahip.platform.core=DEBUG` ile proje içi loglar geliştirme sırasında detaylıdır.

- **Önerilen Sonraki Adımlar**
  - Kullanıcı-rol ilişkisinin Spring Security ile tam entegre edilmesi (role-based authorization).
  - Audit log mekanizmasının kullanıcı/parametre CRUD operasyonlarına bağlanması.
  - REST API’lerin Swagger/OpenAPI ile dokümante edilmesi.
  - Dockerfile ve docker-compose eklenerek local kurulumun daha da kolaylaştırılması.

Bu README, projeyi ilk kez gören bir geliştiricinin hem hızlıca ayağa kaldırabilmesi hem de genel mimariyi ve modülleri kavrayabilmesi için hazırlanmıştır. İhtiyaçlarına göre ek bölümler (ör. mimari diyagramlar, sekans diyagramları, performans notları) kolayca genişletilebilir.


