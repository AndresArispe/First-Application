package com.milankas.training.orderapi.mappers;

import com.milankas.training.orderapi.dto.AddressDto;
import com.milankas.training.orderapi.persistance.model.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto addressToDto(Address address);

    List<AddressDto> toAddressesDto(List<Address> addresses);

    Address toAddress(AddressDto addressDto);
}
