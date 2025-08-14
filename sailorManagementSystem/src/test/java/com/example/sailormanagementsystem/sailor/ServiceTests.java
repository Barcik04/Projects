package com.example.sailormanagementsystem.sailor;

import com.example.sailormanagementsystem.club.Club;
import com.example.sailormanagementsystem.club.ClubDTO;
import com.example.sailormanagementsystem.club.ClubRepository;
import com.example.sailormanagementsystem.club.ClubService;
import com.example.sailormanagementsystem.exception.SameEmailException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
@Transactional
public class ServiceTests {
    @Autowired
    private SailorRepository sailorRepository;

    @Autowired
    private SailorService sailorService;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private ClubService clubService;

    @BeforeEach
    public void clean() {
        sailorRepository.deleteAll();
    }

    @Test
    public void shouldReturnAllSailors() {
        //given
        Sailor sailor1 = new Sailor("Igor", "Barcik", LocalDate.of(2004, 12, 1), "Igor.bb00@gmail.com", "1234");
        Sailor sailor2 = new Sailor("Igora", "Barcika", LocalDate.of(204, 12, 1), "Igor.bb00@gma.com", "12134");
        sailorRepository.save(sailor1);
        sailorRepository.save(sailor2);

        //when
        List<SailorDTO> allSailors = sailorService.findAll();

        //then
        assertThat(allSailors).hasSize(2);
    }

    @Test
    public void shouldReturnAddedSailor() {
        Sailor sailor1 = new Sailor("Igor", "Barcik", LocalDate.of(2004, 12, 1), "Igor.bb00@gmail.com", "1234");


        sailorService.addSailor(sailor1);

        assertThat(sailorRepository.findAll()).extracting(Sailor::getEmail).contains("Igor.bb00@gmail.com");
    }

    @Test
    public void shouldThrowSameEmailException() {
        Sailor sailor1 = new Sailor("Igor", "Barcik", LocalDate.of(2004, 12, 1), "Igor.bb00@gmail.com", "1234");
        Sailor sailor2 = new Sailor("Jacek", "Biba", LocalDate.of(2000, 1, 1), "Igor.bb00@gmail.com", "5678");

        sailorService.addSailor(sailor1);

        assertThatThrownBy(() -> sailorService.addSailor(sailor2))
                .isInstanceOf(SameEmailException.class)
                .hasMessageContaining("Igor.bb00@gmail.com already exists");
    }

    @Test
    public void shouldReturnAllClubs() {
        Club club = new Club("Nautcius", "Olsztyn");
        clubRepository.save(club);

        List<ClubDTO> allClubs = clubService.findAll();

        assertThat(allClubs).hasSize(1);
    }

    @Test
    public void shouldReturnAddedClub() {
        Club club = new Club("Nautcius", "Olsztyn");
        clubRepository.save(club);

        clubService.addClub(club);

        assertThat(clubRepository.findAll()).hasSize(1);
        assertThat(club).extracting(Club::getId).isEqualTo(club.getId());
    }

    @Test
    void shouldAddSailorToClub() {
        Club club = new Club("AZS", "Olsztyn");
        Sailor sailor = new Sailor("Bartek", "Barcik", LocalDate.of(2004, 12, 1), "Igor.bb00@gmaail.com", "1234");
        clubRepository.save(club);
        sailorRepository.save(sailor);

        clubService.addSailorToClub(sailor.getId(), club.getId());

        Club updatedClub = clubRepository.findById(club.getId()).orElseThrow();
        Sailor updatedSailor = sailorRepository.findById(sailor.getId()).orElseThrow();

        assertThat(updatedClub.getSailors()).extracting(Sailor::getEmail).contains("Igor.bb00@gmaail.com");
        assertThat(updatedSailor.getClub()).isEqualTo(updatedClub);
    }

    @Test
    public void shouldDeleteSailorFromClub() {
        Club club = new Club("AZS", "Olsztyn");
        Sailor sailor = new Sailor("Bartek", "Barcik", LocalDate.of(2004, 12, 1), "Igor.bb00@gmaail.com", "1234");
        clubRepository.save(club);
        sailorRepository.save(sailor);

        clubService.addSailorToClub(sailor.getId(), club.getId());
        assertThat(club.getSailors()).extracting(Sailor::getEmail).contains("Igor.bb00@gmaail.com");
        clubService.deleteSailorFromClub(sailor.getId(), club.getId());

        assertThat(club.getSailors()).isEmpty();
        assertThat(sailor.getClub()).isNull();
    }

    @Test
    public void shouldDeleteClub() {
        Club club = new Club("AZS", "Olsztyn");
        Sailor sailor = new Sailor("Bartek", "Barcik", LocalDate.of(2004, 12, 1), "Igor.bb00@gmaail.com", "1234");
        clubRepository.save(club);
        sailorRepository.save(sailor);

        clubService.addSailorToClub(sailor.getId(), club.getId());
        clubService.deleteClub(club.getId());


    }

    @Test
    public void shouldReturnClubByName() {
        Club club = new Club("AZS", "Olsztyn");
        clubRepository.save(club);

        ClubDTO dtoClub = clubService.findClubByName(club.getName());

        assertThat(dtoClub.name()).isEqualTo("AZS");
    }
}
