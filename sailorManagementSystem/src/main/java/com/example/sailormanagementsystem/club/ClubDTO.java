package com.example.sailormanagementsystem.club;

import com.example.sailormanagementsystem.sailor.SailorDTO;

import java.util.List;

public record ClubDTO(
        String name,
        String location,
        List<SailorDTO> sailors
) {
}
