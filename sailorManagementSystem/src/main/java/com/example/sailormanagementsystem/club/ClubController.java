package com.example.sailormanagementsystem.club;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("clubs")
public class ClubController {
    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public List<ClubDTO> findAllClubs() {
        return clubService.findAll();
    }

    @PostMapping
    public ClubDTO addClub(@RequestBody @Valid Club club) {
        return clubService.addClub(club);
    }

    @PutMapping("/{sailorId}/{clubId}")
    public ClubDTO addSailor(@PathVariable @Positive(message = "sailorId must be positive") Long sailorId, @Positive(message = "sailorId must be positive") @PathVariable Long clubId) {
        return clubService.addSailorToClub(sailorId, clubId);
    }

    @DeleteMapping("/remove-sailor/{sailorId}/{clubId}")
    public void deleteSailor(@PathVariable @Positive(message = "sailorId must be positive") Long sailorId, @PathVariable @Positive(message = "sailorId must be positive") Long clubId) {
        clubService.deleteSailorFromClub(sailorId, clubId);
    }

    @DeleteMapping("/delete-club/{clubId}")
    public void deleteClub(@PathVariable @Positive(message = "sailorId must be positive") Long clubId) {
        clubService.deleteClub(clubId);
    }

    @GetMapping("get-by-name/{clubName}")
    public ClubDTO getClubByName(@PathVariable @NotBlank(message = "club name cant be blank") String clubName) {
        return clubService.findClubByName(clubName);
    }
}
