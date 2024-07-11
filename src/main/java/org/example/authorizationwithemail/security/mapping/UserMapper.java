package org.example.authorizationwithemail.security.mapping;

import org.example.authorizationwithemail.security.dto.RegisterReq;
import org.example.authorizationwithemail.security.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    RegisterReq toRegisterReq(User user);

    User toUser(RegisterReq registerReq);

}
