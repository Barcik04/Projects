package com.example.sailormanagementsystem.club;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClubMapper {
    ClubDTO toDto(Club club);
    List<ClubDTO> toDtoList(List<Club> clubs);
}
