package com.app.tmbe.repository;

import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TeamRepository extends JpaRepository<Team, Long> {
  Set<Team> findByIsChecked(boolean b);
}
