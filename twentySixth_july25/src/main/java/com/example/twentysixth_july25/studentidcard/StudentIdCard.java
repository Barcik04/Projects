package com.example.twentysixth_july25.studentidcard;


import com.example.twentysixth_july25.student.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
public class StudentIdCard {
    @Id
    @SequenceGenerator(
            name = "student_id_card_sequence",
            sequenceName = "student_id_card_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_id_card_sequence")
    private Long id;
    @Column(
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String cardNumber;
    @OneToOne
    @JsonBackReference
    @JoinColumn(
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_id_card_student_id_fk"
            ),
            nullable = false,
            unique = true
    )
    private Student student;
    @Column(
            nullable = false
    )
    private ZonedDateTime createdAt;

    public StudentIdCard() {
    }

    @PrePersist
    public void prePersist() {
        createdAt = ZonedDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        if (student != null) {
            student.setStudentIdCard(this);
        }
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
