package com.app.tmbe.repository;

import com.app.tmbe.dataModel.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> { }
