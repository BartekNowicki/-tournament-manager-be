package com.example.tmbe.service;

import com.example.tmbe.dataModel.Player;
import com.example.tmbe.dataModel.Tournament;
import com.example.tmbe.exception.NoEntityFoundCustomException;
import com.example.tmbe.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TournamentService {
  @Autowired TournamentRepository tournamentRepository;
  @Autowired PlayerService playerService;

  public List<Tournament> getAllTournamentsOrderByIdAsc() {
    return tournamentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
  }

  public Optional<Tournament> getTournamentById(long id) {
    return tournamentRepository.findById(id);
  }

  public Tournament deleteTournamentById(long id) throws NoEntityFoundCustomException {
    Optional<Tournament> tournamentToDelete = tournamentRepository.findById(id);
    if (tournamentToDelete.isEmpty()) {
      throw new NoEntityFoundCustomException("No tournament with that id exists: " + id);
    }
    Tournament tournamentToBeDeleted = tournamentToDelete.get();
    // need a new hashset to avoid the concurrent modification exception
    for (Player player : new HashSet<>(tournamentToBeDeleted.getParticipatingPlayers())) {
      tournamentToBeDeleted.removePlayer(player);
    }
    tournamentRepository.delete(tournamentToBeDeleted);
    return tournamentToBeDeleted;
  }

  public Tournament saveOrUpdateTournament(Tournament tournament) {
    Optional<Tournament> tournamentToUpdate = tournamentRepository.findById(tournament.getId());
    if (tournamentToUpdate.isEmpty()) {
      Tournament savedTournament = tournamentRepository.save(tournament);
      return savedTournament;
    } else {
      Tournament t = tournamentToUpdate.get();
      t.setType(tournament.getType());
      t.setComment(tournament.getComment());
      t.setEndDate(tournament.getEndDate());
      t.setStartDate(tournament.getStartDate());
      t.setGroupSize(tournament.getGroupSize());
      t.setParticipatingPlayers(tournament.getParticipatingPlayers());
      return tournamentRepository.save(t);
    }
  }

  public Tournament assignPlayersToTournament(Long tournamentId)
      throws NoEntityFoundCustomException {
    Optional<Tournament> tournamentToUpdate = tournamentRepository.findById(tournamentId);
    if (tournamentToUpdate.isEmpty()) {
      throw new NoEntityFoundCustomException("No tournament with that id exists: " + tournamentId);
    } else {
      Tournament tournamentToAssignAllCheckedPlayersTo = tournamentToUpdate.get();
      Set<Player> participatingPlayers = playerService.findAllByIsChecked(true);
      participatingPlayers.remove(playerService.getPlayerById(-1L));
      tournamentToAssignAllCheckedPlayersTo.setParticipatingPlayers(participatingPlayers);

      for (Player player : tournamentToAssignAllCheckedPlayersTo.getParticipatingPlayers()) {
        player.addTournament(tournamentToAssignAllCheckedPlayersTo);
      }

      Tournament tournamentWithAssignedPlayers =
          saveOrUpdateTournament(tournamentToAssignAllCheckedPlayersTo);

      return tournamentWithAssignedPlayers;
    }
  }
}
