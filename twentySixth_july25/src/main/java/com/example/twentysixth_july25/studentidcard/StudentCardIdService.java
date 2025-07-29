package com.example.twentysixth_july25.studentidcard;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCardIdService {
    private final StudentCardRepository studentCardRepository;

    public StudentCardIdService(StudentCardRepository studentCardRepository) {
        this.studentCardRepository = studentCardRepository;
    }

    public List<StudentIdCard> findAll() {
        return studentCardRepository.findAll();
    }

    public void addCard(StudentIdCard studentIdCard) {
        studentCardRepository.save(studentIdCard);
    }

    public void deleteCard(Long id) {
        studentCardRepository.deleteById(id);
    }
}
