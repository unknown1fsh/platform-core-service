package com.sahip.platform.core.dto;

import com.sahip.platform.core.dto.base.BaseDTO;
import com.sahip.platform.core.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO extends BaseDTO {
    @NotBlank
    @Size(max = 100)
    private String username;
    @Email
    @Size(max = 200)
    private String email;
    private UserStatus status;
}
