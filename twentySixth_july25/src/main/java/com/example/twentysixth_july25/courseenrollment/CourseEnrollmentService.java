package com.example.twentysixth_july25.courseenrollment;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CourseEnrollmentService {
    private final CourseEnrollmentRepository courseEnrollmentRepository;
    public CourseEnrollmentService(CourseEnrollmentRepository courseEnrollmentRepository) {
        this.courseEnrollmentRepository = courseEnrollmentRepository;
    }

    public List<CourseEnrollment> getAllCourseEnrollments() {
        return courseEnrollmentRepository.findAll();
    }

    public void addCourseEnrollment(CourseEnrollment courseEnrollment) {
        courseEnrollmentRepository.save(courseEnrollment);
    }
}
