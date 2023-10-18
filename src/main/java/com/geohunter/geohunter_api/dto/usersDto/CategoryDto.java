package com.geohunter.geohunter_api.dto.usersDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {
    @NotBlank
    @Size(min = 1, max = 15)
    private String category;
}
