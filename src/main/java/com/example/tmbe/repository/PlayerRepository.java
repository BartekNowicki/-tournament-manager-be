package com.example.tmbe.repository;

import com.example.tmbe.dataModel.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {}
