package com.example.tmbe.repository;

import com.example.tmbe.dataModel.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> { }
