package com.example.shorty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shorty.entity.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

}
