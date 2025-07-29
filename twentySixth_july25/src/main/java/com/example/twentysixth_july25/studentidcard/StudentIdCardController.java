package com.example.twentysixth_july25.studentidcard;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student-id-card")
public class StudentIdCardController {
    private final StudentCardIdService studentCardIdService;

    public StudentIdCardController(StudentCardIdService studentCardIdService) {
        this.studentCardIdService = studentCardIdService;
    }

    @GetMapping
    public List<StudentIdCard> getStudentIdCard() {
        return studentCardIdService.findAll();
    }

    @PostMapping
    public void addStudentIdCard(@RequestBody StudentIdCard studentIdCard) {
        studentCardIdService.addCard(studentIdCard);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentIdCard(@PathVariable Long id) {
        studentCardIdService.deleteCard(id);
    }
}
