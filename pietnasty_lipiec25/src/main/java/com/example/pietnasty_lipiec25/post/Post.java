package com.example.pietnasty_lipiec25.post;

public record Post(
        Integer id,
        Integer userId,
        String title,
        String body
) {
}
