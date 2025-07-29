package com.example.twentysixth_july25.student;

import com.example.twentysixth_july25.studentidcard.StudentIdCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findStudentByLastName(String lastName);


    @Query("SELECT s FROM Student s WHERE s.age >= :age AND s.firstName = :firstName")
    List<Student> findAgeGreaterAndFirstNameEquals(@Param("age") int age, @Param("firstName") String firstName);


    @Transactional
    @Modifying
    @Query("DELETE FROM Student s WHERE s.age = ?1")
    void deleteStudentsByAge(int age);


    int countStudentByAge(int age);
}
