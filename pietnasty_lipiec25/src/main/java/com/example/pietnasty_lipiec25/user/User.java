package com.example.pietnasty_lipiec25.user;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "people")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "must not be empty")
    private String firstName;
    @Positive(message = "age has to be above 0")
    private int age;
    @Email(message = "email is not correct")
    private String email;

    public User(int id, String firstName, int age, String email) {
        this.id = id;
        this.firstName = firstName;
        this.age = age;
        this.email = email;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public @Email(message = "email is not correct") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "email is not correct") String email) {
        this.email = email;
    }
}
