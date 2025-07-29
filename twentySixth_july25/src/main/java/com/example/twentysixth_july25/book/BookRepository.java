package com.example.twentysixth_july25.book;

import com.example.twentysixth_july25.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT DISTINCT s FROM Student s JOIN FETCH s.books b WHERE b.bookName = :title")
    List<Student> findByBookTitle(@Param("title") String title);

}
