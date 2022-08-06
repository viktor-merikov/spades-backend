package com.backend.spades.mapper;

import com.backend.spades.dto.AddressDto;
import com.backend.spades.dto.UserDto;
import com.backend.spades.model.Address;
import com.backend.spades.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper {

    @Autowired
    private AddressMapper addressMapper;

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(addressMapper.toDto(user.getAddress()));
        userDto.setRole(user.getRole());
        return userDto;
    }

    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setAddress(addressMapper.toEntity(userDto.getAddress()));
        user.setRole(userDto.getRole());
        return user;
    }

    public List<UserDto> listToDto(List<User> userList) {
        return userList.stream().map(this::toDto).toList();
    }

    public List<User> listToEntity(List<UserDto> userDtoList) {
        return userDtoList.stream().map(this::toEntity).toList();
    }

}

class AddressMapper {
    public AddressDto toDto(Address address) {
        return new AddressDto(
                address.getCity(),
                address.getStreet(),
                address.getNumber(),
                address.getZipcode()
        );
    }

    public Address toEntity(AddressDto addressDto) {
        return new Address(
                addressDto.getCity(),
                addressDto.getStreet(),
                addressDto.getNumber(),
                addressDto.getZipcode()
        );
    }
}