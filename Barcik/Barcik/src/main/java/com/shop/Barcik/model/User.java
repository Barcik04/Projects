package com.shop.Barcik.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Username cant be empty")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "Username cant be empty")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "Username cant be empty") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username cant be empty") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Username cant be empty") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Username cant be empty") String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", username=" + username +
                ", password=" + password + "    ";
    }

}
