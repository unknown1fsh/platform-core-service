-- Roles
CREATE TABLE IF NOT EXISTS role (
    id BIGSERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_role_name UNIQUE (name)
);

-- User roles
CREATE TABLE IF NOT EXISTS user_role (
    id BIGSERIAL NOT NULL,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES user_account (id),
    CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES role (id),
    CONSTRAINT uk_user_role_userid_roleid UNIQUE (user_id, role_id)
);

-- Audit logs
CREATE TABLE IF NOT EXISTS audit_log (
    id BIGSERIAL NOT NULL,
    username VARCHAR(150) NOT NULL,
    action VARCHAR(50) NOT NULL,
    resource VARCHAR(255) NOT NULL,
    details VARCHAR(1000),
    ip_address VARCHAR(50),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_audit_username ON audit_log (username);
CREATE INDEX IF NOT EXISTS idx_audit_action ON audit_log (action);

