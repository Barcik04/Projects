package com.example.twentysixth_july25.studentidcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCardRepository extends JpaRepository<StudentIdCard, Long> {
}
