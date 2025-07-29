package com.example.twentysixth_july25.course;


import com.example.twentysixth_july25.courseenrollment.CourseEnrollment;
import com.example.twentysixth_july25.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "course_sequence")
    private Long id;
    @Column(
            columnDefinition = "TEXT",
            nullable = false,
            unique = true
    )
    private String courseName;
    @Column(
            columnDefinition = "TEXT",
            nullable = false
    )
    private String department;

    @OneToMany(cascade = {CascadeType.MERGE},
            mappedBy = "course")
    @JsonIgnore
    private Set<CourseEnrollment> courseEnrollments = new HashSet<>();

    public Course() {
    }

    public Set<CourseEnrollment> getCourseEnrollments() {
        return courseEnrollments;
    }

    public void setCourseEnrollments(Set<CourseEnrollment> courseEnrollments) {
        this.courseEnrollments = courseEnrollments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void addCourseEnrollment(Student student) {
        courseEnrollments.add(new CourseEnrollment(student, this));
    }

    public void removeCourseEnrollment(Student student) {
        courseEnrollments.removeIf(enrollment -> enrollment.getStudent().equals(student));
    }


}
