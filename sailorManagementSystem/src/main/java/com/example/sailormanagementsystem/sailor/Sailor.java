package com.example.sailormanagementsystem.sailor;


import com.example.sailormanagementsystem.club.Club;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;


import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "sailors")
public class Sailor {
    @Id
    @SequenceGenerator(name = "sailor_sequence", sequenceName = "sailor_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sailor_sequence")
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "first name cant be blank")
    private String firstName;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "last name cant be blank")
    private String lastName;

    @Column(nullable = false)
    @NotNull(message = "date of birth is missing")
    @Past(message = "date of birth must be a past date")
    private LocalDate dateOfBirth;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "emial cant be blank")
    @Email(message = "invalid email")
    private String email;

    @Column(length = 60)
    @NotBlank(message = "password cant be blank")
    private String password;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(name = "sailor_club_id_fk"))
    @JsonIgnore
    private Club club;


    public Sailor(String firstName, String lastName, LocalDate dateOfBirth, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
    }

    public Sailor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "first name cant be blank") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "first name cant be blank") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "last name cant be blank") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "last name cant be blank") String lastName) {
        this.lastName = lastName;
    }

    public @NotNull(message = "date of birth is missing") @Past(message = "date of birth must be a past date") LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotNull(message = "date of birth is missing") @Past(message = "date of birth must be a past date") LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotBlank(message = "emial cant be blank") @Email(message = "invalid email") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "emial cant be blank") @Email(message = "invalid email") String email) {
        this.email = email;
    }

    public @NotBlank(message = "password cant be blank") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "password cant be blank") String password) {
        this.password = password;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sailor sailor = (Sailor) o;
        return Objects.equals(id, sailor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
