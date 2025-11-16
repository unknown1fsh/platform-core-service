-- Cleanup migration for existing installations.
-- MySQL 8 ve Flyway birlikte kullanılırken 'IF NOT EXISTS' sentaksı hata verdiği için
-- bu migration yeni kurulumlarda NO-OP (iş yapmayan) halde bırakıldı.
-- Gerekirse manuel bakım için aşağıdaki örnekler referans olarak tutulabilir.

-- ALTER TABLE user_account ADD COLUMN deleted_at DATETIME NULL;
-- ALTER TABLE parameter ADD COLUMN deleted_at DATETIME NULL;
-- CREATE UNIQUE INDEX uk_user_username ON user_account (username);
-- CREATE INDEX idx_user_status ON user_account (status);
-- CREATE UNIQUE INDEX uk_parameter_category_code ON parameter (category, code);
-- CREATE INDEX idx_parameter_active ON parameter (active);

