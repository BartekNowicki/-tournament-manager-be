package com.app.tmbe.service;

import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.SinglesTournament;
import com.app.tmbe.exception.NoEntityFoundCustomException;
import com.app.tmbe.repository.TournamentRepository;
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

  public List<SinglesTournament> getAllTournamentsOrderByIdAsc() {
    return tournamentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
  }

  public Optional<SinglesTournament> getTournamentById(long id) {
    return tournamentRepository.findById(id);
  }

  public SinglesTournament deleteTournamentById(long id) throws NoEntityFoundCustomException {
    Optional<SinglesTournament> tournamentToDelete = tournamentRepository.findById(id);
    if (tournamentToDelete.isEmpty()) {
      throw new NoEntityFoundCustomException("No tournament with that id exists: " + id);
    }
    SinglesTournament singlesTournamentToBeDeleted = tournamentToDelete.get();
    // need a new hashset to avoid the concurrent modification exception
    for (Player player : new HashSet<>(singlesTournamentToBeDeleted.getParticipatingPlayers())) {
      singlesTournamentToBeDeleted.removePlayer(player);
    }
    tournamentRepository.delete(singlesTournamentToBeDeleted);
    return singlesTournamentToBeDeleted;
  }

  public SinglesTournament saveOrUpdateTournament(SinglesTournament singlesTournament) {
    Optional<SinglesTournament> tournamentToUpdate =
        tournamentRepository.findById(singlesTournament.getId());
    if (tournamentToUpdate.isEmpty()) {
      SinglesTournament savedSinglesTournament = tournamentRepository.save(singlesTournament);
      return savedSinglesTournament;
    } else {
      SinglesTournament t = tournamentToUpdate.get();
      t.setType(singlesTournament.getType());
      t.setComment(singlesTournament.getComment());
      t.setEndDate(singlesTournament.getEndDate());
      t.setStartDate(singlesTournament.getStartDate());
      t.setGroupSize(singlesTournament.getGroupSize());
      t.setParticipatingPlayers(singlesTournament.getParticipatingPlayers());
      return tournamentRepository.save(t);
    }
  }

  public SinglesTournament assignPlayersToSinglesTournament(Long tournamentId)
      throws NoEntityFoundCustomException {
    Optional<SinglesTournament> tournamentToUpdate = tournamentRepository.findById(tournamentId);
    if (tournamentToUpdate.isEmpty()) {
      throw new NoEntityFoundCustomException("No tournament with that id exists: " + tournamentId);
    } else {
      SinglesTournament singlesTournamentToAssignAllCheckedPlayersTo = tournamentToUpdate.get();
      Set<Player> playersRemovedFromTournament =
          new HashSet<>(singlesTournamentToAssignAllCheckedPlayersTo.getParticipatingPlayers());
      Set<Player> participatingPlayers = playerService.findAllByIsChecked(true);
      playersRemovedFromTournament.removeAll(participatingPlayers);
      participatingPlayers.remove(playerService.getPlayerById(-1L));
      singlesTournamentToAssignAllCheckedPlayersTo.setParticipatingPlayers(participatingPlayers);

      for (Player player : singlesTournamentToAssignAllCheckedPlayersTo.getParticipatingPlayers()) {
        player.addSinglesTournament(singlesTournamentToAssignAllCheckedPlayersTo);
      }

      for (Player player : playersRemovedFromTournament) {
        player.removeSinglesTournament(singlesTournamentToAssignAllCheckedPlayersTo);
      }

      SinglesTournament singlesTournamentWithAssignedPlayers =
          saveOrUpdateTournament(singlesTournamentToAssignAllCheckedPlayersTo);

      return singlesTournamentWithAssignedPlayers;
    }
  }
}
