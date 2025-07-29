package com.example.twentysixth_july25.student;


import com.example.twentysixth_july25.book.Book;
import com.example.twentysixth_july25.book.BookRepository;
import com.example.twentysixth_july25.studentidcard.StudentIdCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;

    public StudentService(StudentRepository studentRepository, BookRepository bookRepository) {
        this.studentRepository = studentRepository;
        this.bookRepository = bookRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByLastName(String lastName) {
        return studentRepository.findStudentByLastName(lastName);
    }

    public List<Student> findAgeGreaterAndFirstNameEquals(int age, String firstName) {
        return studentRepository.findAgeGreaterAndFirstNameEquals(age, firstName);
    }

    public List<Student> getAllStudentsSorted(String direction,String sortField) {
        Sort.Direction sortDirection = Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.ASC);
        return studentRepository.findAll(Sort.by(sortDirection, sortField));
    }

    private static final int MAX_PAGE_SIZE = 100;
    public Page<Student> getStudentsByPage(Pageable pageable) {
        if (pageable.getPageSize() > MAX_PAGE_SIZE) {
            throw new IllegalArgumentException("Maximum page size is " + MAX_PAGE_SIZE);
        }
        return studentRepository.findAll(pageable);
    }

    public int countStudents(int age) {
        return studentRepository.countStudentByAge(age);
    }


    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student addBookFromId(Long studentId, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Student student = studentRepository.findById(studentId);
        student.addBook(book);

        return studentRepository.save(student);
    }

    public Student updateStudent(Student student, int id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student with id: " + id + " not found"));

        return studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public void deleteStudentsByAge(int age) {
        studentRepository.deleteStudentsByAge(age);
    }


}
