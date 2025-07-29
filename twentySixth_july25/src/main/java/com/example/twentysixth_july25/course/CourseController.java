package com.example.twentysixth_july25.course;

import com.example.twentysixth_july25.student.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @GetMapping("get_course_with_students/{courseId}")
    public CourseWithStudentsDTO getCourseWithStudents(@PathVariable Long courseId) {
        return courseService.getCourseWithStudents(courseId);
    }
}
