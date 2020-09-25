package com.milankas.training.orderapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milankas.training.orderapi.persistance.model.Order;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
public class AddressDto {

    private UUID id;

    @NotNull(message = "Address Line 1 it's required")
    @Size(min = 10, max = 50, message = "Address Line 1 must be have 10 characters minimum and max 50 characters")
    private String addressLine1;

    @NotNull(message = "Address Line 2 it's required")
    @Size(min = 10, max = 50, message = "Address Line 2 must be have 10 characters minimum and max 50 characters")
    private String addressLine2;

    @NotNull(message = "Contact name it's required")
    @Size(min = 5, max = 20, message = "Contact name must be have 5 characters minimum and max 20 characters")
    private String contactName;

    @NotNull(message = "Contact phone number it's required")
    @Size(min = 7, max = 12, message = "Contact phone number must be have 7 characters minimum and max 12 characters")
    private String contactPhoneNumber;

    @NotNull(message = "State it's required")
    @Size(min = 4, max = 20, message = "State must be have 4 characters minimum and max 20 characters")
    private String state;

    @NotNull(message = "City it's required")
    @Size(min = 4, max = 20, message = "City must be have 4 characters minimum and max 20 characters")
    private String city;

    @NotNull(message = "Zip code it's required")
    @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$", message = "Invalid Zip code format")
    private String zipCode;

    @NotNull(message = "Country code it's required")
    @Size(min = 2, max = 2, message = "Country code must be have 2 characters")
    @Pattern(regexp = "[A-Z].*[A-Z]", message = "Invalid country code format")
    private String countryCode;

    @JsonIgnore
    private Order order;
}
