package com.example.twentysixth_july25.student;


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

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
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
