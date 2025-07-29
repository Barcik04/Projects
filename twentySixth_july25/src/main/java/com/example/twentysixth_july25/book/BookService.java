package com.example.twentysixth_july25.book;

import com.example.twentysixth_july25.student.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository BookRepository) {
        this.bookRepository = BookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Student> findByBookName(String bookName) {
        return bookRepository.findByBookTitle(bookName);
    }
}
