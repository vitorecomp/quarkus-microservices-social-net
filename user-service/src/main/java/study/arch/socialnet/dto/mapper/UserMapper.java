package study.arch.socialnet.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import study.arch.socialnet.domain.User;
import study.arch.socialnet.dto.UserCreateDTO;
import study.arch.socialnet.dto.UserDTO;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    UserDTO toResource(User user);

    List<UserDTO> toResourceList(List<User> users);

    User toEntity(UserDTO userDTO);

    User toEntity(UserCreateDTO userCreateDTO);
}