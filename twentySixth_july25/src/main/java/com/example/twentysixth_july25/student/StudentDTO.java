package com.example.twentysixth_july25.student;

import com.example.twentysixth_july25.book.Book;

import java.util.List;

public record StudentDTO(
        String firstName,
        String lastName,
        String email,
        Integer age,
        List<Book> books
) {
}
