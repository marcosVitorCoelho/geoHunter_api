package com.geohunter.geohunter_api.dto.usersDto;

import com.geohunter.geohunter_api.models.AddressModel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegularUserDto {
    @NotBlank
    @Size(min = 1, max = 15)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 15)
    private String lastName;

    @NotBlank
    @Size(min = 10, max = 10)
    private String rg;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank
    @Email(message = "Email format not allowed")
    private String email;

    @NotBlank
    private String phoneNumber;

    @Valid
    private AddressModel address;

    @NotBlank
    private String birthday;
}
