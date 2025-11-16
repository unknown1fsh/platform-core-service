package com.sahip.platform.core.mapper;

import com.sahip.platform.core.dto.UserDTO;
import com.sahip.platform.core.entity.UserAccount;
import com.sahip.platform.core.mapper.config.MappingConfig;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface UserMapper extends BaseMapper<UserAccount, UserDTO> {
}
