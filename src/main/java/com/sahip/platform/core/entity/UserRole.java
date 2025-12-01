package com.sahip.platform.core.entity;

import com.sahip.platform.core.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_role",
        uniqueConstraints = @UniqueConstraint(name = "uk_user_role_userid_roleid", columnNames = {"user_id", "role_id"}))
@Getter
@Setter
public class UserRole extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}


