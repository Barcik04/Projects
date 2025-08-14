package com.example.sailormanagementsystem.exception;

import java.time.ZonedDateTime;
import java.util.List;

public record ApiError(
        String message,
        int StatusCode,
        ZonedDateTime dateTime,
        List<String> errors
) {
}
