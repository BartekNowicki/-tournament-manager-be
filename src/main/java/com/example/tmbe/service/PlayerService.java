package com.example.tmbe.service;

import com.example.tmbe.dataModel.Player;
import com.example.tmbe.exception.NoEntityFoundCustomException;
import com.example.tmbe.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
  @Autowired PlayerRepository playerRepository;

  public List<Player> getAllPlayers() {
    return playerRepository.findAll();
  }

  public Optional<Player> getPlayerById(long id) {
    return playerRepository.findById(id);
  }

  public Player deletePlayerById(long id) throws NoEntityFoundCustomException {
    Optional<Player> playerToDelete = playerRepository.findById(id);
    if (playerToDelete.isEmpty()) {
      throw new NoEntityFoundCustomException("No player with that id exists: " + id);
    }
    playerRepository.delete(playerToDelete.get());
    return playerToDelete.get();
  }

  public Player saveOrUpdatePlayer(Player player) {
    Optional<Player> playerToUpdate = playerRepository.findById(player.getId());
    if (playerToUpdate.isEmpty()) {
      return playerRepository.save(player);
    } else {
      Player p = playerToUpdate.get();
      p.setFirstName(player.getFirstName());
      p.setLastName(player.getLastName());
      p.setComment(player.getComment());
      p.setIsChecked(player.getIsChecked());
      p.setStrength(player.getStrength());
      return playerRepository.save(p);
    }
  }
}
