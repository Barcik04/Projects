package com.example.twentysixth_july25.course;

import com.example.twentysixth_july25.student.Student;
import com.example.twentysixth_july25.student.StudentDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public CourseWithStudentsDTO getCourseWithStudents(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        List<StudentDTO> students = course.getCourseEnrollments().stream()
                .map(enrollment -> {
                    Student s = enrollment.getStudent();
                    return new StudentDTO(
                            s.getFirstName(),
                            s.getLastName(),
                            s.getEmail(),
                            s.getAge(),
                            s.getBooks()
                    );
                }).toList();

        return new CourseWithStudentsDTO(
                course.getCourseName(),
                course.getDepartment(),
                students
        );
    }
}
