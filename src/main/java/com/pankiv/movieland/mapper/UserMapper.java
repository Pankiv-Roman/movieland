package com.pankiv.movieland.mapper;

import com.pankiv.movieland.dto.UserDto;
import com.pankiv.movieland.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
