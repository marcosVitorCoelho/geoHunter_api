package com.geohunter.geohunter_api.models;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;


@Data
@Embeddable
public class AddressModel  {
    @NotBlank(message = "street is required")
    private String street;

    @NotBlank(message = "city is required")
    private String city;

    @NotBlank(message = "state is required")
    private String state;

    @NotBlank(message = "number is required")
    private String number;

    @NotBlank(message = "zip code is required")
    private String zipCode;
}