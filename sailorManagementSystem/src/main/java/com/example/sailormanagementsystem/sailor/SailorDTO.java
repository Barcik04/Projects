package com.example.sailormanagementsystem.sailor;

import java.time.LocalDate;

public record SailorDTO(
        String firstName,
        String lastName,
        LocalDate dateOfBirth
) {
}