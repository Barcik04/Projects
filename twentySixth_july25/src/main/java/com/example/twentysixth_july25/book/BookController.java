package com.example.twentysixth_july25.book;


import com.example.twentysixth_july25.student.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @GetMapping("/find-student-by-book/{bookName}")
    public List<Student> findStudentByBook(@PathVariable String bookName) {
        return bookService.findByBookName(bookName);
    }
}
