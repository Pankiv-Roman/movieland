package com.pankiv.movieland.mapper;

import com.pankiv.movieland.dto.CountryDto;
import com.pankiv.movieland.dto.UserDto;
import com.pankiv.movieland.entity.Country;
import com.pankiv.movieland.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);

    User toEntity(UserDto userDto);

    List<User> toEntityList(List<UserDto> usersDtoList);
}
