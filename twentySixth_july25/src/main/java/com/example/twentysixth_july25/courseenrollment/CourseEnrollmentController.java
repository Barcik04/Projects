package com.example.twentysixth_july25.courseenrollment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class CourseEnrollmentController {
    private final CourseEnrollmentService courseEnrollmentService;

    public CourseEnrollmentController(CourseEnrollmentService courseEnrollmentService) {
        this.courseEnrollmentService = courseEnrollmentService;
    }

    @GetMapping
    public List<CourseEnrollment> getEnrollments() {
        return courseEnrollmentService.getAllCourseEnrollments();
    }

    @PostMapping
    public void addEnrollment(@RequestBody CourseEnrollment courseEnrollment) {
        courseEnrollmentService.addCourseEnrollment(courseEnrollment);
    }

}
