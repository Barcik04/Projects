package com.example.sailormanagementsystem.club;

import com.example.sailormanagementsystem.exception.SameValueViolation;
import com.example.sailormanagementsystem.sailor.Sailor;
import com.example.sailormanagementsystem.sailor.SailorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private final SailorRepository sailorRepository;
    private final ClubMapper clubMapper;

    public ClubService(ClubRepository clubRepository, SailorRepository sailorRepository, ClubMapper clubMapper) {
        this.clubRepository = clubRepository;
        this.sailorRepository = sailorRepository;
        this.clubMapper = clubMapper;
    }

    public List<ClubDTO> findAll() {
        return clubMapper.toDtoList(clubRepository.findAll());
    }

    public ClubDTO addClub(Club club) {
        if (clubRepository.findClubByName(club.getName()).isPresent()) {
            throw new SameValueViolation("club: " + club.getName() + " already exists");
        }
        return clubMapper.toDto(clubRepository.save(club));
    }

    @Transactional
    public ClubDTO addSailorToClub(Long sailorId, Long clubId) {
        Sailor sailor = sailorRepository.findById(sailorId)
                .orElseThrow(() -> new NoSuchElementException("Sailor " + sailorId + " not found"));
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new NoSuchElementException("Club " + clubId + " not found"));

        if (club.equals(sailor.getClub())) {
            return clubMapper.toDto(club);
        }

        if (sailor.getClub() != null) {
            sailor.getClub().removeSailor(sailor);
        }

        club.addSailor(sailor);
        sailorRepository.save(sailor);
        return clubMapper.toDto(club);
    }

    @Transactional
    public void deleteSailorFromClub(Long sailorId, Long clubId) {
        Sailor sailor = sailorRepository.findById(sailorId)
                .orElseThrow(() -> new NoSuchElementException("sailor with id: " + sailorId + " not found"));
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new NoSuchElementException("club with id: " + clubId + " not found"));

        if (!club.equals(sailor.getClub())) {
            throw new IllegalArgumentException("Sailor" + sailor + " is not part of the club: " + club);
        }

        club.removeSailor(sailor);
        sailorRepository.save(sailor);
    }


    @Transactional
    public void deleteClub(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new NoSuchElementException("club not found"));

        for (Sailor sailor : new ArrayList<>(club.getSailors())) {
            club.removeSailor(sailor);
        }


        clubRepository.deleteById(clubId);
    }

    public ClubDTO findClubByName(String clubName) {
        Club club = clubRepository.findClubByName(clubName)
                .orElseThrow(() -> new NoSuchElementException("Club not found"));
        return clubMapper.toDto(club);
    }
}
