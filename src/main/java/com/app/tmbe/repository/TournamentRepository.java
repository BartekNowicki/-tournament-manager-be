package com.app.tmbe.repository;

import com.app.tmbe.dataModel.SinglesTournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<SinglesTournament, Long> { }
