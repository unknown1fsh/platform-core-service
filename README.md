# 🚀 Platform Core Service

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen)
![Java](https://img.shields.io/badge/Java-17-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)
![License](https://img.shields.io/badge/License-Free-lightgrey)

**Enterprise-grade platform core service with modern admin UI and REST API**

[English](#english) • [Türkçe](#türkçe)

</div>

---

## English

### 📋 Overview

**Platform Core Service** is a comprehensive enterprise application platform built with Spring Boot, featuring a modern admin dashboard and robust REST API. It provides user management, role-based access control, parameter configuration, and audit logging capabilities.

### ✨ Key Features

- **🔐 User Management**
  - Complete CRUD operations for user accounts
  - User status management (ACTIVE, INACTIVE, BLOCKED)
  - Advanced filtering and pagination
  - Secure password handling

- **👥 Role-Based Access Control**
  - Flexible role management system
  - User-role assignment
  - Ready for Spring Security integration

- **⚙️ Parameter Management**
  - Category-based parameter organization
  - System configuration and feature flags
  - Dynamic application settings

- **📊 Audit Logging**
  - Comprehensive activity tracking
  - User action logging
  - IP address and timestamp tracking

- **🎨 Modern Admin UI**
  - Beautiful dark-themed dashboard
  - Responsive design
  - Glassmorphism effects and gradients
  - Intuitive navigation

### 🛠️ Technology Stack

| Category | Technology |
|----------|-----------|
| **Backend** | Java 17, Spring Boot 3.3.0 |
| **Security** | Spring Security 6 |
| **Database** | PostgreSQL 17 |
| **ORM** | Spring Data JPA, Hibernate |
| **Migration** | Flyway 11.0.0 |
| **Templating** | Thymeleaf |
| **Mapping** | MapStruct |
| **Build Tool** | Maven 3.9+ |

### 📦 Prerequisites

- **Java 17** or higher
- **Maven 3.9+** (or use included Maven wrapper)
- **PostgreSQL 17** or higher

### 🚀 Quick Start

#### 1. Database Setup

Create the PostgreSQL database:

```sql
CREATE DATABASE platform_core_service ENCODING 'UTF8';
```

#### 2. Configuration

Update `src/main/resources/application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/platform_core_service
spring.datasource.username=postgres
spring.datasource.password=your_password
```

#### 3. Run the Application

Using Maven wrapper:

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

Or with Maven:

```bash
mvn clean spring-boot:run
```

#### 4. Access the Application

- **Web UI**: http://localhost:8080
- **API Health**: http://localhost:8080/api/health
- **Login**: http://localhost:8080/login

**Default Credentials:**
- Username: `admin`
- Password: `Admin123!`

### 📁 Project Structure

```
platform-core-service/
├── src/
│   ├── main/
│   │   ├── java/com/sahip/platform/core/
│   │   │   ├── client/          # External API clients
│   │   │   ├── config/          # Configuration classes
│   │   │   ├── controller/      # REST & UI controllers
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── entity/          # JPA entities
│   │   │   ├── enums/           # Enumerations
│   │   │   ├── exception/       # Exception handlers
│   │   │   ├── mapper/          # MapStruct mappers
│   │   │   ├── repository/      # Spring Data repositories
│   │   │   ├── service/         # Business logic
│   │   │   └── specification/   # JPA specifications
│   │   └── resources/
│   │       ├── db/migration/    # Flyway migrations
│   │       ├── static/          # CSS, JS files
│   │       └── templates/      # Thymeleaf templates
│   └── test/                    # Test classes
├── pom.xml
└── README.md
```

### 🗄️ Database Migrations

Flyway migrations are automatically executed on application startup:

- `V1__baseline.sql` - Creates `user_account` and `parameter` tables
- `V2__roles_and_audit_tables.sql` - Creates `role`, `user_role`, and `audit_log` tables
- `V3__seed_users_parameters_audit.sql` - Seeds initial data (users, roles, parameters, audit logs)

### 🔒 Security

- Spring Security 6 integration
- Form-based authentication
- Role-based access control (ready for implementation)
- Secure password storage
- Session management

### 📝 API Endpoints

#### REST API

- `GET /api/health` - Health check endpoint
- `GET /api/users` - List users (with pagination and filtering)
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user
- `GET /api/parameters` - List parameters
- `GET /api/roles` - List roles

#### Web UI

- `/` - Dashboard
- `/login` - Login page
- `/users` - User management
- `/parameters` - Parameter management
- `/roles` - Role management
- `/audit-logs` - Audit log viewer

### 🧪 Development

#### Running Tests

```bash
mvn test
```

#### Building the Project

```bash
mvn clean package
```

#### Development Mode

The project includes Spring Boot DevTools for hot reloading during development.

### 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [Flyway Documentation](https://flywaydb.org/documentation/)
- [MapStruct Documentation](https://mapstruct.org/documentation/stable/reference/html/)

### 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

### 📄 License

This project is free to use and modify.

---

## Türkçe

### 📋 Genel Bakış

**Platform Core Service**, Spring Boot ile geliştirilmiş, modern bir admin paneli ve güçlü REST API sunan kapsamlı bir kurumsal uygulama platformudur. Kullanıcı yönetimi, rol tabanlı erişim kontrolü, parametre yapılandırması ve denetim kayıtları özelliklerini sağlar.

### ✨ Temel Özellikler

- **🔐 Kullanıcı Yönetimi**
  - Kullanıcı hesapları için tam CRUD işlemleri
  - Kullanıcı durum yönetimi (AKTİF, PASİF, BLOKELİ)
  - Gelişmiş filtreleme ve sayfalama
  - Güvenli şifre işleme

- **👥 Rol Tabanlı Erişim Kontrolü**
  - Esnek rol yönetim sistemi
  - Kullanıcı-rol atama
  - Spring Security entegrasyonu için hazır

- **⚙️ Parametre Yönetimi**
  - Kategori bazlı parametre organizasyonu
  - Sistem yapılandırması ve özellik bayrakları
  - Dinamik uygulama ayarları

- **📊 Denetim Kayıtları**
  - Kapsamlı aktivite takibi
  - Kullanıcı eylem kayıtları
  - IP adresi ve zaman damgası takibi

- **🎨 Modern Admin Arayüzü**
  - Güzel koyu temalı dashboard
  - Duyarlı tasarım
  - Glassmorphism efektleri ve gradyanlar
  - Sezgisel navigasyon

### 🛠️ Teknoloji Yığını

| Kategori | Teknoloji |
|----------|-----------|
| **Backend** | Java 17, Spring Boot 3.3.0 |
| **Güvenlik** | Spring Security 6 |
| **Veritabanı** | PostgreSQL 17 |
| **ORM** | Spring Data JPA, Hibernate |
| **Migration** | Flyway 11.0.0 |
| **Şablonlama** | Thymeleaf |
| **Mapping** | MapStruct |
| **Build Aracı** | Maven 3.9+ |

### 📦 Ön Gereksinimler

- **Java 17** veya üzeri
- **Maven 3.9+** (veya dahil edilen Maven wrapper)
- **PostgreSQL 17** veya üzeri

### 🚀 Hızlı Başlangıç

#### 1. Veritabanı Kurulumu

PostgreSQL veritabanını oluşturun:

```sql
CREATE DATABASE platform_core_service ENCODING 'UTF8';
```

#### 2. Yapılandırma

`src/main/resources/application.properties` dosyasını veritabanı bilgilerinizle güncelleyin:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/platform_core_service
spring.datasource.username=postgres
spring.datasource.password=şifreniz
```

#### 3. Uygulamayı Çalıştırma

Maven wrapper kullanarak:

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

Veya Maven ile:

```bash
mvn clean spring-boot:run
```

#### 4. Uygulamaya Erişim

- **Web Arayüzü**: http://localhost:8080
- **API Sağlık Kontrolü**: http://localhost:8080/api/health
- **Giriş**: http://localhost:8080/login

**Varsayılan Giriş Bilgileri:**
- Kullanıcı adı: `admin`
- Şifre: `Admin123!`

### 📁 Proje Yapısı

```
platform-core-service/
├── src/
│   ├── main/
│   │   ├── java/com/sahip/platform/core/
│   │   │   ├── client/          # Harici API istemcileri
│   │   │   ├── config/          # Yapılandırma sınıfları
│   │   │   ├── controller/      # REST ve UI controller'ları
│   │   │   ├── dto/             # Veri Transfer Nesneleri
│   │   │   ├── entity/          # JPA entity'leri
│   │   │   ├── enums/           # Enum'lar
│   │   │   ├── exception/       # Hata yöneticileri
│   │   │   ├── mapper/          # MapStruct mapper'ları
│   │   │   ├── repository/      # Spring Data repository'leri
│   │   │   ├── service/         # İş mantığı
│   │   │   └── specification/   # JPA spesifikasyonları
│   │   └── resources/
│   │       ├── db/migration/    # Flyway migration'ları
│   │       ├── static/          # CSS, JS dosyaları
│   │       └── templates/       # Thymeleaf şablonları
│   └── test/                    # Test sınıfları
├── pom.xml
└── README.md
```

### 🗄️ Veritabanı Migration'ları

Flyway migration'ları uygulama başlatıldığında otomatik olarak çalıştırılır:

- `V1__baseline.sql` - `user_account` ve `parameter` tablolarını oluşturur
- `V2__roles_and_audit_tables.sql` - `role`, `user_role` ve `audit_log` tablolarını oluşturur
- `V3__seed_users_parameters_audit.sql` - Başlangıç verilerini ekler (kullanıcılar, roller, parametreler, denetim kayıtları)

### 🔒 Güvenlik

- Spring Security 6 entegrasyonu
- Form tabanlı kimlik doğrulama
- Rol tabanlı erişim kontrolü (uygulama için hazır)
- Güvenli şifre saklama
- Oturum yönetimi

### 📝 API Endpoint'leri

#### REST API

- `GET /api/health` - Sağlık kontrolü endpoint'i
- `GET /api/users` - Kullanıcı listesi (sayfalama ve filtreleme ile)
- `POST /api/users` - Yeni kullanıcı oluştur
- `PUT /api/users/{id}` - Kullanıcı güncelle
- `DELETE /api/users/{id}` - Kullanıcı sil
- `GET /api/parameters` - Parametre listesi
- `GET /api/roles` - Rol listesi

#### Web Arayüzü

- `/` - Dashboard
- `/login` - Giriş sayfası
- `/users` - Kullanıcı yönetimi
- `/parameters` - Parametre yönetimi
- `/roles` - Rol yönetimi
- `/audit-logs` - Denetim kayıtları görüntüleyici

### 🧪 Geliştirme

#### Testleri Çalıştırma

```bash
mvn test
```

#### Projeyi Derleme

```bash
mvn clean package
```

#### Geliştirme Modu

Proje, geliştirme sırasında hot reload için Spring Boot DevTools içerir.

### 📚 Ek Kaynaklar

- [Spring Boot Dokümantasyonu](https://spring.io/projects/spring-boot)
- [Spring Security Dokümantasyonu](https://spring.io/projects/spring-security)
- [Flyway Dokümantasyonu](https://flywaydb.org/documentation/)
- [MapStruct Dokümantasyonu](https://mapstruct.org/documentation/stable/reference/html/)

### 🤝 Katkıda Bulunma

Katkılarınız memnuniyetle karşılanır! Lütfen bir Pull Request göndermekten çekinmeyin.

### 📄 Lisans

Bu proje kullanım ve değiştirme için ücretsizdir.

---

<div align="center">

**Made with ❤️ using Spring Boot**

</div>
