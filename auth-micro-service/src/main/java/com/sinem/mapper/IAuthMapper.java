package com.sinem.mapper;

import com.sinem.dto.request.DoRegisterRequestDto;
import com.sinem.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth authFromDto(final DoRegisterRequestDto dto);
}
