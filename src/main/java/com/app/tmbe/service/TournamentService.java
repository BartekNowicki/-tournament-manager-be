package com.app.tmbe.service;

import com.app.tmbe.dataModel.DoublesTournament;
import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.SinglesTournament;
import com.app.tmbe.dataModel.Team;
import com.app.tmbe.dataModel.Tournament;
import com.app.tmbe.exception.NoEntityFoundCustomException;
import com.app.tmbe.repository.DoublesTournamentRepository;
import com.app.tmbe.repository.SinglesTournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TournamentService {
  @Autowired SinglesTournamentRepository singlesTournamentRepository;
  @Autowired DoublesTournamentRepository doublesTournamentRepository;
  @Autowired PlayerService playerService;
  @Autowired TeamService teamService;

  public List<Tournament> getAllTournamentsOrderByIdAsc() {
    List<Tournament> tournaments = new ArrayList<>();
    List<SinglesTournament> singlesTournaments =
        singlesTournamentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    List<DoublesTournament> doublesTournaments =
        doublesTournamentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    tournaments.addAll(singlesTournaments);
    tournaments.addAll(doublesTournaments);
    return tournaments;
  }

  public Optional<SinglesTournament> getSinglesTournamentById(long id) {
    return singlesTournamentRepository.findById(id);
  }

  public Optional<DoublesTournament> getDoublesTournamentById(long id) {
    return doublesTournamentRepository.findById(id);
  }

  public SinglesTournament deleteSinglesTournamentById(long id)
      throws NoEntityFoundCustomException {
    Optional<SinglesTournament> tournamentToDelete = singlesTournamentRepository.findById(id);
    if (tournamentToDelete.isEmpty()) {
      throw new NoEntityFoundCustomException("No singles tournament with that id exists: " + id);
    }
    SinglesTournament singlesTournamentToBeDeleted = tournamentToDelete.get();
    // need a new hashset to avoid the concurrent modification exception
    for (Player player : new HashSet<>(singlesTournamentToBeDeleted.getParticipatingPlayers())) {
      singlesTournamentToBeDeleted.removePlayer(player);
    }
    singlesTournamentRepository.delete(singlesTournamentToBeDeleted);
    return singlesTournamentToBeDeleted;
  }

  public DoublesTournament deleteDoublesTournamentById(long id)
      throws NoEntityFoundCustomException {
    Optional<DoublesTournament> tournamentToDelete = doublesTournamentRepository.findById(id);
    if (tournamentToDelete.isEmpty()) {
      throw new NoEntityFoundCustomException("No doubles tournament with that id exists: " + id);
    }
    DoublesTournament doublesTournamentToBeDeleted = tournamentToDelete.get();
    // need a new hashset to avoid the concurrent modification exception
    for (Team team : new HashSet<>(doublesTournamentToBeDeleted.getParticipatingTeams())) {
      doublesTournamentToBeDeleted.removeTeam(team);
    }
    doublesTournamentRepository.delete(doublesTournamentToBeDeleted);
    return doublesTournamentToBeDeleted;
  }

  private void updatePlayersOnAddedOrUpdatedTournament(
      Set<Player> players, SinglesTournament tournament) {
    for (Player player : players) {
      player.addSinglesTournament(tournament);
      playerService.saveOrUpdatePlayer(player);
    }
  }

  private void updateTeamsOnAddedOrUpdatedTournament(
      Set<Team> teams, DoublesTournament tournament) {
    for (Team team : teams) {
      team.addDoublesTournament(tournament);
      teamService.saveOrUpdateTeam(team);
    }
  }

  public SinglesTournament saveOrUpdateSinglesTournament(SinglesTournament singlesTournament) {
    Optional<SinglesTournament> tournamentToUpdate =
        singlesTournamentRepository.findById(singlesTournament.getId());
    if (tournamentToUpdate.isEmpty()) {
      SinglesTournament savedSinglesTournament =
          singlesTournamentRepository.save(singlesTournament);
      updatePlayersOnAddedOrUpdatedTournament(
          singlesTournament.getParticipatingPlayers(), savedSinglesTournament);
      return savedSinglesTournament;
    } else {
      SinglesTournament t = tournamentToUpdate.get();
      t.setType(singlesTournament.getType());
      t.setComment(singlesTournament.getComment());
      t.setEndDate(singlesTournament.getEndDate());
      t.setStartDate(singlesTournament.getStartDate());
      t.setGroupSize(singlesTournament.getGroupSize());
      t.setParticipatingPlayers(singlesTournament.getParticipatingPlayers());

      SinglesTournament savedSinglesTournament = singlesTournamentRepository.save(t);

      return savedSinglesTournament;
    }
  }

  public DoublesTournament saveOrUpdateDoublesTournament(DoublesTournament doublesTournament) {
    Optional<DoublesTournament> tournamentToUpdate =
        doublesTournamentRepository.findById(doublesTournament.getId());
    if (tournamentToUpdate.isEmpty()) {
      DoublesTournament savedDoublesTournament =
          doublesTournamentRepository.save(doublesTournament);
      updateTeamsOnAddedOrUpdatedTournament(
          doublesTournament.getParticipatingTeams(), savedDoublesTournament);
      return savedDoublesTournament;
    } else {
      DoublesTournament t = tournamentToUpdate.get();
      t.setType(doublesTournament.getType());
      t.setComment(doublesTournament.getComment());
      t.setEndDate(doublesTournament.getEndDate());
      t.setStartDate(doublesTournament.getStartDate());
      t.setGroupSize(doublesTournament.getGroupSize());
      t.setParticipatingTeams(doublesTournament.getParticipatingTeams());

      DoublesTournament savedDoublesTournament = doublesTournamentRepository.save(t);

      return savedDoublesTournament;
    }
  }

  public SinglesTournament assignPlayersToSinglesTournament(Long tournamentId)
      throws NoEntityFoundCustomException {
    Optional<SinglesTournament> tournamentToUpdate =
        singlesTournamentRepository.findById(tournamentId);
    if (tournamentToUpdate.isEmpty()) {
      throw new NoEntityFoundCustomException("No tournament with that id exists: " + tournamentId);
    } else {
      SinglesTournament tournamentToAssign = tournamentToUpdate.get();

      Set<Player> included = new HashSet<>(playerService.findAllByIsChecked(true));
      Set<Player> notIncluded = new HashSet<>(tournamentToAssign.getParticipatingPlayers());
      notIncluded.removeAll(included);
//      included.remove(playerService.getPlayerById(-1L));
      tournamentToAssign.setParticipatingPlayers(included);

      for (Player player : tournamentToAssign.getParticipatingPlayers()) {
        player.addSinglesTournament(tournamentToAssign);
      }

      for (Player player : notIncluded) {
        player.removeSinglesTournament(tournamentToAssign);
      }

      SinglesTournament singlesTournamentWithAssignedPlayers =
          saveOrUpdateSinglesTournament(tournamentToAssign);

      return singlesTournamentWithAssignedPlayers;
    }
  }

  public DoublesTournament assignTeamsToDoublesTournament(Long tournamentId)
      throws NoEntityFoundCustomException {
    Optional<DoublesTournament> tournamentToUpdate =
        doublesTournamentRepository.findById(tournamentId);
    if (tournamentToUpdate.isEmpty()) {
      throw new NoEntityFoundCustomException("No tournament with that id exists: " + tournamentId);
    } else {
      DoublesTournament tournamentToAssign = tournamentToUpdate.get();

      Set<Team> included = teamService.findAllByIsChecked(true);
      Set<Team> notIncluded = new HashSet<>(tournamentToAssign.getParticipatingTeams());
      notIncluded.removeAll(included);
      included.remove(teamService.getTeamById(-1L));
      tournamentToAssign.setParticipatingTeams(included);

      for (Team team : tournamentToAssign.getParticipatingTeams()) {
        team.addDoublesTournament(tournamentToAssign);
      }

      for (Team team : notIncluded) {
        team.removeDoublesTournament(tournamentToAssign);
      }

      DoublesTournament doublesTournamentWithAssignedTeams =
          saveOrUpdateDoublesTournament(tournamentToAssign);

      return doublesTournamentWithAssignedTeams;
    }
  }
}
