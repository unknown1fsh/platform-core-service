package com.sahip.platform.core.dto;

import com.sahip.platform.core.dto.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParameterDTO extends BaseDTO {
    @NotBlank
    @Size(max = 100)
    private String category;
    @NotBlank
    @Size(max = 100)
    private String code;
    @NotBlank
    @Size(max = 500)
    private String value;
    @Size(max = 500)
    private String description;
    private Boolean active;
}

