package com.app.tmbe.repository;

import com.app.tmbe.dataModel.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Set<Player> findByIsChecked(boolean b);
}
