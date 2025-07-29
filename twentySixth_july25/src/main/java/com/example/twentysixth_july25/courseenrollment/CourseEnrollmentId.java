package com.example.twentysixth_july25.courseenrollment;


import jakarta.persistence.Embeddable;

import java.util.Objects;


@Embeddable
public class CourseEnrollmentId {
    private Long courseId;
    private Long studentId;

    public CourseEnrollmentId(Long courseId, Long studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public CourseEnrollmentId() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEnrollmentId that = (CourseEnrollmentId) o;
        return Objects.equals(courseId, that.courseId) && Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, studentId);
    }
}
