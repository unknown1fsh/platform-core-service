-- Users
CREATE TABLE IF NOT EXISTS user_account (
  id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(200),
  status VARCHAR(30) NOT NULL,
  created_at DATETIME,
  updated_at DATETIME,
  deleted_at DATETIME NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_user_username (username),
  KEY idx_user_status (status)
);

-- Parameters
CREATE TABLE IF NOT EXISTS parameter (
  id BIGINT NOT NULL AUTO_INCREMENT,
  category VARCHAR(100) NOT NULL,
  code VARCHAR(100) NOT NULL,
  value VARCHAR(500) NOT NULL,
  description VARCHAR(500),
  active BIT(1) NOT NULL,
  created_at DATETIME,
  updated_at DATETIME,
  deleted_at DATETIME NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_parameter_category_code (category, code),
  KEY idx_parameter_active (active)
);

