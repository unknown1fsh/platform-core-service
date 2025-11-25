package com.sahip.platform.core.mapper;

import com.sahip.platform.core.dto.RoleDTO;
import com.sahip.platform.core.entity.Role;
import com.sahip.platform.core.mapper.config.MappingConfig;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface RoleMapper extends BaseMapper<Role, RoleDTO> {
}


