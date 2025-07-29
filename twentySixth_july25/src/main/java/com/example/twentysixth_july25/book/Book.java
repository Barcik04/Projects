package com.example.twentysixth_july25.book;


import com.example.twentysixth_july25.student.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "book_sequence")
    private Long id;
    @NotEmpty(message = "please insert book name")
    @NotNull(message = "book cant be null")
    @Column(
            columnDefinition = "TEXT"
    )
    private String bookName;
    @Column(nullable = false)
    ZonedDateTime createdAt;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "book_student_id_fk"
            ),
            unique = true
    )
    @JsonBackReference
    private Student student;

    public Book() {
    }

    public Book(Student student, String bookName) {
        this.student = student;
        this.bookName = bookName;
    }

    @PrePersist
    public void prePersist() {
        createdAt = ZonedDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "please insert book name") @NotNull(message = "book cant be null") String getBookName() {
        return bookName;
    }

    public void setBookName(@NotEmpty(message = "please insert book name") @NotNull(message = "book cant be null") String bookName) {
        this.bookName = bookName;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
