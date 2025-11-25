# Personel Backend – Java 21 Upgrade ✅

Bu proje Java 21'e (en güncel LTS) yükseltildi ve test edildi.

## Yükseltme Özeti
- **Hedef Java Sürümü**: Java 21 (LTS - Uzun Dönem Destek)
- **Önceki Sürüm**: Java 17
- **Spring Boot Sürümü**: 3.2.0 (Java 21 uyumlu, değişiklik gerekmedi)
- **Derleme Durumu**: ✅ BAŞARILI
- **Test Durumu**: ✅ TÜM TESTLER GEÇTI
- **Tarih**: 6 Kasım 2025

## Yapılan Değişiklikler
- Maven özellikleri `java.version`, `maven.compiler.source`, ve `maven.compiler.target` → 21
- Maven Compiler Plugin `source=21` ve `target=21` olarak yapılandırıldı
- JDK 21 (Adoptium Temurin 21.0.9+10) kuruldu ve doğrulandı
- Tüm bağımlılıklar Java 21 ile uyumluluk açısından test edildi

## Ön Gereksinimler
Bilgisayarınızda JDK 21 kurulu olmalı ve Maven bu JDK'yı kullanacak şekilde yapılandırılmalıdır.

### Seçenek A: Chocolatey ile Kurulum (Windows - Yönetici Gerekli)
```powershell
choco install temurin21 -y
# veya: choco install microsoft-openjdk21 -y
```

Bu oturumda Java 21'i varsayılan yapın (birden fazla JDK varsa):
```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21"  # kurulum yolunuzu kontrol edin
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
```

Doğrulama:
```powershell
java -version
mvn -v
```
İkisi de Java 21 göstermeli.

### Seçenek B: Manuel Kurulum (Yönetici Gerektirmez)
JDK 21'i kullanıcı klasörüne indirip kurun:
```powershell
$dest="$env:USERPROFILE\jdk\jdk21"
$zip="$env:TEMP\jdk21.zip"
New-Item -ItemType Directory -Force -Path $dest | Out-Null
Invoke-WebRequest -Uri "https://api.adoptium.net/v3/binary/latest/21/ga/windows/x64/jdk/hotspot/normal/eclipse" -OutFile $zip
Expand-Archive -Force -Path $zip -DestinationPath $dest
$env:JAVA_HOME=(Get-ChildItem $dest -Directory | Select-Object -First 1 -ExpandProperty FullName)
$env:PATH = "$env:JAVA_HOME\bin;" + $env:PATH
```

Doğrulama:
```powershell
java -version  # 21.0.x göstermeli
mvn -v         # Java version: 21.0.x göstermeli
```

### Seçenek C: Maven Toolchains (Çoklu JDK için Önerilir)
`%USERPROFILE%\.m2\toolchains.xml` dosyası oluşturun:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<toolchains xmlns="http://maven.apache.org/TOOLCHAINS/1.1.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/TOOLCHAINS/1.1.0 https://maven.apache.org/xsd/toolchains-1.1.0.xsd">
  <toolchain>
    <type>jdk</type>
    <provides>
      <version>21</version>
      <vendor>adoptium</vendor>
    </provides>
    <configuration>
      <jdkHome>C:\\Program Files\\Eclipse Adoptium\\jdk-21</jdkHome>
    </configuration>
  </toolchain>
</toolchains>
```
`jdkHome` değerini kendi JDK 21 kurulum yolunuzla değiştirin.

## Derleme ve Test
```powershell
# Testler olmadan derleme
mvn -q -DskipTests clean package

# Testlerle birlikte derleme
mvn -q clean package

# Sadece testleri çalıştırma
mvn -q test
```

## Uygulamayı Çalıştırma
```powershell
# Maven ile
mvn spring-boot:run

# Veya JAR ile
java -jar target\personel-backend-1.0.0-SNAPSHOT.jar
```

## Sorun Giderme
- **Hata: "invalid target release: 21"** → Maven'in Java 21 kullandığından emin olun (`mvn -v` ile kontrol edin).
- **MapStruct/Lombok annotation processing hataları** → Lombok 1.18.30+ ve MapStruct 1.5.5.Final+ kullanıldığından emin olun. `pom.xml`'de zaten ayarlandı.
- **Kalıcı toolchain sorunları** → Yukarıdaki Seçenek C'yi (Maven Toolchains) kullanın.

## Bağımlılık Uyumluluğu
| Bağımlılık | Sürüm | Java 21 Uyumluluğu |
|------------|-------|-------------------|
| Spring Boot | 3.2.0 | ✅ Tam uyumlu |
| Lombok | 1.18.30 | ✅ Java 21 desteği |
| MapStruct | 1.5.5.Final | ✅ Java 21 desteği |
| JWT (jjwt) | 0.12.3 | ✅ Uyumlu |
| MySQL Connector | Spring yönetimi | ✅ Uyumlu |
| SpringDoc OpenAPI | 2.3.0 | ✅ Uyumlu |

## Sonraki Adımlar (Opsiyonel)
1. **Spring Boot güncelleme**: 3.2.x'in son yaması (güvenlik yamalarını almak için)
2. **Kalıcı JAVA_HOME**: Sistem çevre değişkenlerinde kalıcı yapın
3. **CI/CD Pipeline**: GitHub Actions/Jenkins'te Java 21 kullanacak şekilde güncelleyin
4. **Performans testi**: Java 21'in yeni özelliklerini (Virtual Threads, vb.) değerlendirin

---
**Not**: Bu yükseltme başarıyla test edildi. Tüm testler geçti ve uygulama Java 21 ile sorunsuz çalışıyor.
