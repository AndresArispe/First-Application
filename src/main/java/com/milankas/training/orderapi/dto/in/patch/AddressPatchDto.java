package com.milankas.training.orderapi.dto.in.patch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milankas.training.orderapi.persistance.model.Order;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
public class AddressPatchDto {

    private UUID id;

    @Size(min = 10, max = 50, message = "Address Line 1 must be have 10 characters minimum and max 50 characters")
    private String addressLine1;

    @Size(min = 10, max = 50, message = "Address Line 2 must be have 10 characters minimum and max 50 characters")
    private String addressLine2;

    @Size(min = 5, max = 20, message = "Contact name must be have 5 characters minimum and max 20 characters")
    private String contactName;

    @Size(min = 7, max = 12, message = "Contact phone number must be have 7 characters minimum and max 12 characters")
    private String contactPhoneNumber;

    @Size(min = 4, max = 20, message = "State must be have 4 characters minimum and max 20 characters")
    private String state;

    @Size(min = 4, max = 20, message = "City must be have 4 characters minimum and max 20 characters")
    private String city;

    @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$", message = "Invalid Zip code format")
    private String zipCode;

    @Size(min = 2, max = 2, message = "Country code must be have 2 characters")
    @Pattern(regexp = "[A-Z].*[A-Z]", message = "Invalid country code format")
    private String countryCode;

    @JsonIgnore
    private Order order;
}
