package com.example.sailormanagementsystem.club;

import com.example.sailormanagementsystem.sailor.Sailor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clubs")
public class Club {
    @Id
    @SequenceGenerator(name = "club_sequence", sequenceName = "club_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "club_sequence")
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "club name cant be blank")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "location cant be blank")
    private String location;

    @OneToMany(mappedBy = "club", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Sailor> sailors = new ArrayList<>();

    public Club(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Club() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "club name cant be blank") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "club name cant be blank") String name) {
        this.name = name;
    }

    public @NotBlank(message = "location cant be blank") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "location cant be blank") String location) {
        this.location = location;
    }

    public List<Sailor> getSailors() {
        return sailors;
    }

    public void setSailors(List<Sailor> sailors) {
        this.sailors = sailors;
    }

    //HELPER METHODS
    public void addSailor(Sailor sailor) {
        sailors.add(sailor);
        sailor.setClub(this);
    }

    public void removeSailor(Sailor sailor) {
        sailors.remove(sailor);
        sailor.setClub(null);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Club club = (Club) o;
        return Objects.equals(id, club.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
