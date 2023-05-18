package com.example.tmbe.service;

import com.example.tmbe.dataModel.Player;
import com.example.tmbe.dataModel.Tournament;
import com.example.tmbe.exception.NoEntityFoundCustomException;
import com.example.tmbe.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlayerService {
  @Autowired PlayerRepository playerRepository;

  public List<Player> getAllPlayersOrderByIdAsc() {
    return playerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
  }

  public Optional<Player> getPlayerById(long id) {
    return playerRepository.findById(id);
  }

  public Player deletePlayerById(long id) throws NoEntityFoundCustomException {
    Optional<Player> playerToDelete = playerRepository.findById(id);
    if (playerToDelete.isEmpty()) {
      throw new NoEntityFoundCustomException("No player with that id exists: " + id);
    }
    Player playerToBeDeleted = playerToDelete.get();
    for (Tournament tournament : playerToBeDeleted.getPlayedTournaments()) {
      playerToBeDeleted.removeTournament(tournament);
    }
    playerRepository.delete(playerToBeDeleted);
    return playerToBeDeleted;
  }

  public Player saveOrUpdatePlayer(Player player) {
    Optional<Player> playerToUpdate = playerRepository.findById(player.getId());
    if (playerToUpdate.isEmpty()) {
      for (Tournament tournament : player.getPlayedTournaments()) {
        tournament.addPlayer(player);
      }
      return playerRepository.save(player);
    } else {
      Player p = playerToUpdate.get();
      Set<Tournament> totalTournamentsPlayed = new HashSet<>(player.getPlayedTournaments());
      totalTournamentsPlayed.addAll(p.getPlayedTournaments());
      p.setFirstName(player.getFirstName());
      p.setLastName(player.getLastName());
      p.setComment(player.getComment());
      p.setIsChecked(player.getIsChecked());
      p.setStrength(player.getStrength());
      p.setPlayedTournaments(totalTournamentsPlayed);
      return playerRepository.save(p);
    }
  }

  public Set<Player> findAllByIsChecked(boolean b) {
    return playerRepository.findByIsChecked(true);
  }
}
