-- Users
CREATE TABLE IF NOT EXISTS user_account (
  id BIGSERIAL NOT NULL,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(200),
  status VARCHAR(30) NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  deleted_at TIMESTAMP NULL,
  PRIMARY KEY (id),
  CONSTRAINT uk_user_username UNIQUE (username)
);

CREATE INDEX IF NOT EXISTS idx_user_status ON user_account (status);

-- Parameters
CREATE TABLE IF NOT EXISTS parameter (
  id BIGSERIAL NOT NULL,
  category VARCHAR(100) NOT NULL,
  code VARCHAR(100) NOT NULL,
  value VARCHAR(500) NOT NULL,
  description VARCHAR(500),
  active BOOLEAN NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  deleted_at TIMESTAMP NULL,
  PRIMARY KEY (id),
  CONSTRAINT uk_parameter_category_code UNIQUE (category, code)
);

CREATE INDEX IF NOT EXISTS idx_parameter_active ON parameter (active);
