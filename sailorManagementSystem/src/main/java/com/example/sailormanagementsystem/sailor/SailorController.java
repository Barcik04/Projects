package com.example.sailormanagementsystem.sailor;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sailors")
public class SailorController {
    private final SailorService sailorService;

    public SailorController(SailorService sailorService) {
        this.sailorService = sailorService;
    }

    @GetMapping
    public List<SailorDTO> getAllSailors() {
        return sailorService.findAll();
    }

    @PostMapping
    public SailorDTO addSailor(@RequestBody @Valid Sailor sailor) {
        return sailorService.addSailor(sailor);
    }
}
