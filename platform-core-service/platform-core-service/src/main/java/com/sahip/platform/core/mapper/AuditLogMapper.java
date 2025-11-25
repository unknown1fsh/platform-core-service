package com.sahip.platform.core.mapper;

import com.sahip.platform.core.dto.AuditLogDTO;
import com.sahip.platform.core.entity.AuditLog;
import com.sahip.platform.core.mapper.config.MappingConfig;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface AuditLogMapper extends BaseMapper<AuditLog, AuditLogDTO> {
}


