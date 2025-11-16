-- Example cleanup/missing columns safe alterations
ALTER TABLE user_account ADD COLUMN IF NOT EXISTS deleted_at DATETIME NULL;
ALTER TABLE parameter ADD COLUMN IF NOT EXISTS deleted_at DATETIME NULL;

-- Ensure indexes
CREATE UNIQUE INDEX IF NOT EXISTS uk_user_username ON user_account (username);
CREATE INDEX IF NOT EXISTS idx_user_status ON user_account (status);
CREATE UNIQUE INDEX IF NOT EXISTS uk_parameter_category_code ON parameter (category, code);
CREATE INDEX IF NOT EXISTS idx_parameter_active ON parameter (active);

