package com.example.twentysixth_july25.course;

import com.example.twentysixth_july25.student.StudentDTO;

import java.util.List;

public record CourseWithStudentsDTO(
        String courseName,
        String department,
        List<StudentDTO> students
) {
}
