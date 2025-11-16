package com.sahip.platform.core.dto;

import com.sahip.platform.core.dto.base.BaseDTO;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends BaseDTO {

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String description;
}


