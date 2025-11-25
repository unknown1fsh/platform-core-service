-- Seed roles
INSERT INTO role (name, description, created_at)
VALUES
    ('ROLE_ADMIN', 'Tam yetkili yönetici', NOW()),
    ('ROLE_OPERATOR', 'Operasyon ve parametre yönetimi', NOW()),
    ('ROLE_VIEWER', 'Sadece okuma yetkisi', NOW())
ON DUPLICATE KEY UPDATE description = VALUES(description);

-- Seed users (passwords burada placeholder, uygulama icinde encode edilebilir)
INSERT INTO user_account (username, password, email, status, created_at)
VALUES
    ('admin', '{noop}Admin123!', 'admin@example.com', 'ACTIVE', NOW()),
    ('operator1', '{noop}Operator123!', 'operator1@example.com', 'ACTIVE', NOW()),
    ('viewer1', '{noop}Viewer123!', 'viewer1@example.com', 'ACTIVE', NOW()),
    ('demo_user', '{noop}Demo123!', 'demo@example.com', 'INACTIVE', NOW())
ON DUPLICATE KEY UPDATE email = VALUES(email);

-- Map users to roles
INSERT INTO user_role (user_id, role_id, created_at)
SELECT u.id, r.id, NOW()
FROM user_account u
JOIN role r ON (
    (u.username = 'admin' AND r.name = 'ROLE_ADMIN')
    OR (u.username = 'operator1' AND r.name = 'ROLE_OPERATOR')
    OR (u.username = 'viewer1' AND r.name = 'ROLE_VIEWER')
)
ON DUPLICATE KEY UPDATE deleted_at = NULL;

-- Seed parameters
INSERT INTO parameter (category, code, value, description, active, created_at)
VALUES
    ('GENERAL', 'SITE_NAME', 'Platform Core', 'Sitenin görünen adı', b'1', NOW()),
    ('GENERAL', 'SUPPORT_EMAIL', 'support@example.com', 'Destek e-posta adresi', b'1', NOW()),
    ('SECURITY', 'MAX_LOGIN_ATTEMPT', '5', 'Maksimum hatalı giriş denemesi', b'1', NOW()),
    ('SECURITY', 'PASSWORD_MIN_LENGTH', '8', 'Şifre minimum uzunluğu', b'1', NOW()),
    ('FEATURE', 'FEATURE_NEW_DASHBOARD_ENABLED', 'true', 'Yeni dashboard arayüzü aktif mi', b'1', NOW()),
    ('FEATURE', 'FEATURE_AUDIT_LOG_EXPORT', 'false', 'Audit log dışa aktarma özelliği', b'0', NOW()),
    ('LIMITS', 'DEFAULT_PAGE_SIZE', '10', 'Varsayılan sayfa boyutu', b'1', NOW())
ON DUPLICATE KEY UPDATE value = VALUES(value);

-- Seed audit logs (örnek bir kaç kayıt)
INSERT INTO audit_log (username, action, resource, details, ip_address, created_at)
VALUES
    ('admin', 'LOGIN_SUCCESS', '/login', 'Yönetici başarılı giriş yaptı', '127.0.0.1', NOW()),
    ('admin', 'CREATE_USER', '/users', 'operator1 kullanıcısı oluşturuldu', '127.0.0.1', NOW()),
    ('admin', 'UPDATE_PARAM', '/parameters', 'MAX_LOGIN_ATTEMPT parametresi 5 olarak güncellendi', '127.0.0.1', NOW()),
    ('operator1', 'LOGIN_SUCCESS', '/login', 'Operatör başarılı giriş yaptı', '127.0.0.1', NOW()),
    ('operator1', 'UPDATE_PARAM', '/parameters', 'FEATURE_NEW_DASHBOARD_ENABLED true yapıldı', '127.0.0.1', NOW()),
    ('viewer1', 'LOGIN_SUCCESS', '/login', 'Viewer kullanıcı giriş yaptı', '127.0.0.1', NOW());


