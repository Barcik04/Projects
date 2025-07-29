package com.example.twentysixth_july25.student;

import com.example.twentysixth_july25.book.Book;
import com.example.twentysixth_july25.course.Course;
import com.example.twentysixth_july25.studentidcard.StudentIdCard;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence")
    private Long id;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotEmpty(message = "first name cant be empty")
    private String firstName;

    @Column(
            columnDefinition = "TEXT",
            unique = true)
    private String lastName;

    @Column(nullable = false)
    @Positive(message = "age has to be positive")
    @Max(value = 200, message = "age cant be above 200")
    private int age;

    @Column(
            nullable = false,
            unique = true,
            columnDefinition = "TEXT"
    )
    @Email(message = "invalid email")
    @NotEmpty(message = "email cant be empty")
    private String email;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private StudentIdCard studentIdCard;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Book> books = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "student_enrollment",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    foreignKey = @ForeignKey(
                            name = "enrollment_student_id_fk"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id",
                    foreignKey = @ForeignKey(
                            name = "enrollment_course_id_fk"
                    )
            )
    )
    @JsonManagedReference
    private Set<Course> courses = new HashSet<>();



    public Student(Long id, String firstName, String lastName, int age, String email, StudentIdCard studentIdCard) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.studentIdCard = studentIdCard;
    }

    public Student() {

    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentIdCard getStudentIdCard() {
        return studentIdCard;
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if(!books.contains(book)) {
            books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            book.setStudent(null);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(email, student.email) && Objects.equals(studentIdCard, student.studentIdCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, email, studentIdCard);
    }
}
