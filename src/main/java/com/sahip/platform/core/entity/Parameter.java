package com.sahip.platform.core.entity;

import com.sahip.platform.core.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parameter")
@Getter
@Setter
public class Parameter extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false, length = 100)
    private String code;

    @Column(nullable = false, length = 500)
    private String value;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean active = true;
}
