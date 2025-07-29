package com.example.twentysixth_july25.student;


import com.example.twentysixth_july25.book.Book;

import java.util.List;

public record StudentBookDTO(
        String firstName,
        String lastName,
        List<Book> books
) {
}
