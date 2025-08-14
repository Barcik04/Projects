package com.example.sailormanagementsystem.sailor;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SailorMapper {
    SailorDTO toDto(Sailor sailor);
    List<SailorDTO> toDtoList(List<Sailor> sailors);
}
