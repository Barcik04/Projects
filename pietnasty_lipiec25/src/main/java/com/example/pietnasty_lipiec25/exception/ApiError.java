package com.example.pietnasty_lipiec25.exception;

import java.time.ZonedDateTime;
import java.util.List;

public record ApiError(
        String message,
        int statusCode,
        ZonedDateTime DateTime,
        List<String> errors
) {
}
