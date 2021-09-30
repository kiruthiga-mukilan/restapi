package com.kiruthi.restapi.mappers;


import com.kiruthi.restapi.dtos.UserMsDto;
import com.kiruthi.restapi.entities.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	//User To UserMsDto
	@Mappings({
		@Mapping(source= "userid", target="id"),
		@Mapping(source = "username", target="name")
	})
	UserMsDto userToUserMsDto(User user);

	//List<User> to List<UserMsDto>
	List<UserMsDto> usersToUserDtos(List<User> users);

}
