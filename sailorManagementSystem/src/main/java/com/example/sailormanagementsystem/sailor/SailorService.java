package com.example.sailormanagementsystem.sailor;

import com.example.sailormanagementsystem.exception.SameEmailException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SailorService {
    private final SailorRepository sailorRepository;
    private final SailorMapper sailorMapper;

    public SailorService(SailorRepository sailorRepository, SailorMapper sailorMapper) {
        this.sailorRepository = sailorRepository;
        this.sailorMapper = sailorMapper;
    }

    public List<SailorDTO> findAll() {
        return sailorMapper.toDtoList(sailorRepository.findAll());
    }

    public SailorDTO addSailor(Sailor sailor) {
        if (sailorRepository.existsSailorByEmail(sailor.getEmail())) {
            throw new SameEmailException("Email: " + sailor.getEmail() + " already exists");
        }
        return sailorMapper.toDto(sailorRepository.save(sailor));
    }
}
