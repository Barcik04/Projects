package com.example.twentysixth_july25.student;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable @Positive int id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/by-last-name")
    public Optional<Student> getStudentByLastName(@RequestParam String lastName) {
        return studentService.getStudentByLastName(lastName);
    }

    @GetMapping("/by-age-firstName")
    public List<Student> getStudentByAgeGreaterAndFirstNameEquals(@RequestParam int age, @RequestParam String firstName) {
        return studentService.findAgeGreaterAndFirstNameEquals(age, firstName);
    }

    @GetMapping("/sort")
    public List<Student> getStudentsSorted(@RequestParam(defaultValue = "asc") String direction, @RequestParam(defaultValue = "age") String field) {
        return studentService.getAllStudentsSorted(direction, field);
    }

    @GetMapping("/page")
    public Page<Student> getStudentsPage(@PageableDefault(size = 5, sort = "age", direction = Sort.Direction.ASC) Pageable pageable) {
        return studentService.getStudentsByPage(pageable);
    }

    @GetMapping("/count")
    public int getStudentsCount(@RequestParam int age) {
        return studentService.countStudents(age);
    }

    @PostMapping(path = "/students", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student addStudent(@RequestBody @Valid Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody @Valid Student student) {
        return studentService.updateStudent(student, id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable @Positive int id) {
        studentService.deleteStudent(id);
    }

    @DeleteMapping("/by-age")
    public void deleteStudentsByAge(@RequestParam int age) {
        studentService.deleteStudentsByAge(age);
    }
}
