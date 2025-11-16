package com.sahip.platform.core.dto;

import com.sahip.platform.core.dto.base.BaseDTO;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuditLogDTO extends BaseDTO {

    private String username;
    private String action;
    private String resource;
    private String details;
    private String ipAddress;
}


