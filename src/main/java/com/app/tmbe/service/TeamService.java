package com.app.tmbe.service;

import com.app.tmbe.dataModel.Team;
import com.app.tmbe.dataModel.DoublesTournament;
import com.app.tmbe.exception.NoEntityFoundCustomException;
import com.app.tmbe.repository.TeamRepository;
import com.app.tmbe.utils.GrouperInterface;
import com.app.tmbe.utils.TeamGrouper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamService {
  @Autowired TeamRepository teamRepository;

  public List<Team> getAllTeams() {
    return teamRepository.findAll();
  }

  public List<Team> getAllTeamsOrderByIdAsc() {
    return teamRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
  }

  public Optional<Team> getTeamById(long id) {
    return teamRepository.findById(id);
  }

  public Team deleteTeamById(long id) throws NoEntityFoundCustomException {
    Optional<Team> teamToDelete = teamRepository.findById(id);
    if (teamToDelete.isEmpty()) {
      throw new NoEntityFoundCustomException("No team with that id exists: " + id);
    }
    Team teamToBeDeleted = teamToDelete.get();
    // need a new hashset to avoid the concurrent modification exception
    for (DoublesTournament doublesTournament :
        new HashSet<>(teamToBeDeleted.getPlayedDoublesTournaments())) {
      teamToBeDeleted.removeDoublesTournament(doublesTournament);
    }
    teamRepository.delete(teamToBeDeleted);
    return teamToBeDeleted;
  }

  public Team saveOrUpdateTeam(Team team) {
    Optional<Team> teamToUpdate = teamRepository.findById(team.getId());
    if (teamToUpdate.isEmpty()) {
      for (DoublesTournament doublesTournament : team.getPlayedDoublesTournaments()) {
        doublesTournament.addTeam(team);
      }
      return teamRepository.save(team);
    } else {
      Team t = teamToUpdate.get();
      Set<DoublesTournament> totalTournamentsPlayed =
          new HashSet<>(team.getPlayedDoublesTournaments());
      totalTournamentsPlayed.addAll(t.getPlayedDoublesTournaments());
      t.setPlayerOneId(team.getPlayerOneId());
      t.setPlayerTwoId(team.getPlayerTwoId());
      t.setComment(team.getComment());
      t.setIsChecked(team.getIsChecked());
      t.setStrength(team.getStrength());
      t.setPlayedDoublesTournaments(totalTournamentsPlayed);
      return teamRepository.save(t);
    }
  }

  public Set<Team> findAllByIsChecked(boolean b) {
    return teamRepository.findByIsChecked(true);
  }

  public Map<Integer, Set<Team>> groupTeams(int groupSize) {
    Set<Team> teams = getAllTeams().stream().collect(Collectors.toSet());
    GrouperInterface teamGrouper = new TeamGrouper(teams, groupSize);
    return teamGrouper.groupTeams();
  }
}
