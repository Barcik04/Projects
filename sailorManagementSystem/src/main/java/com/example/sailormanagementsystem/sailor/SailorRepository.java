package com.example.sailormanagementsystem.sailor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SailorRepository extends JpaRepository<Sailor, Long> {
    boolean existsSailorByEmail(String email);
}
