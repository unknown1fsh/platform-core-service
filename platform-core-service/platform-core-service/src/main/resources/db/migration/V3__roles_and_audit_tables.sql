-- Roles
CREATE TABLE IF NOT EXISTS role (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_role_name (name)
);

-- User roles
CREATE TABLE IF NOT EXISTS user_role (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES user_account (id),
    CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES role (id),
    UNIQUE KEY uk_user_role_userid_roleid (user_id, role_id)
);

-- Audit logs
CREATE TABLE IF NOT EXISTS audit_log (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(150) NOT NULL,
    action VARCHAR(50) NOT NULL,
    resource VARCHAR(255) NOT NULL,
    details VARCHAR(1000),
    ip_address VARCHAR(50),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME NULL,
    PRIMARY KEY (id),
    KEY idx_audit_username (username),
    KEY idx_audit_action (action)
);


