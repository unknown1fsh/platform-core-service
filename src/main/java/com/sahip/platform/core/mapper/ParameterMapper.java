package com.sahip.platform.core.mapper;

import com.sahip.platform.core.dto.ParameterDTO;
import com.sahip.platform.core.entity.Parameter;
import com.sahip.platform.core.mapper.config.MappingConfig;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface ParameterMapper extends BaseMapper<Parameter, ParameterDTO> {
}

