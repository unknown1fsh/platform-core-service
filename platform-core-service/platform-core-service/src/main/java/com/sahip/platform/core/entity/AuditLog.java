package com.sahip.platform.core.entity;

import com.sahip.platform.core.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "audit_log")
@Getter
@Setter
public class AuditLog extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String username;

    @Column(nullable = false, length = 50)
    private String action;

    @Column(nullable = false, length = 255)
    private String resource;

    @Column(length = 1000)
    private String details;

    @Column(name = "ip_address", length = 50)
    private String ipAddress;
}


