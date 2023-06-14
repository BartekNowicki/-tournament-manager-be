package com.app.tmbe.service;

import com.app.tmbe.dataModel.GroupInSingles;
import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.SinglesTournament;
import com.app.tmbe.dataModel.Team;
import com.app.tmbe.exception.NoEntityFoundCustomException;
import com.app.tmbe.repository.GroupRepository;
import com.app.tmbe.repository.PlayerRepository;

import com.app.tmbe.utils.GrouperInterface;
import com.app.tmbe.utils.PlayerGrouper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerService {
  @Autowired PlayerRepository playerRepository;
  @Autowired GroupRepository groupRepository;

  public List<Player> getAllPlayers() {
    return playerRepository.findAll();
  }

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
    // need a new hashset to avoid the concurrent modification exception
    for (SinglesTournament singlesTournament :
        new HashSet<>(playerToBeDeleted.getPlayedSinglesTournaments())) {
      playerToBeDeleted.removeSinglesTournament(singlesTournament);
    }
    playerRepository.delete(playerToBeDeleted);
    return playerToBeDeleted;
  }

  public Player saveOrUpdatePlayer(Player player) {
    Optional<Player> playerToUpdate = playerRepository.findById(player.getId());
    if (playerToUpdate.isEmpty()) {
      for (SinglesTournament singlesTournament : player.getPlayedSinglesTournaments()) {
        singlesTournament.addPlayer(player);
      }
      return playerRepository.save(player);
    } else {
      Player p = playerToUpdate.get();
      Set<SinglesTournament> totalTournamentsPlayed =
          new HashSet<>(player.getPlayedSinglesTournaments());
      totalTournamentsPlayed.addAll(p.getPlayedSinglesTournaments());
      p.setFirstName(player.getFirstName());
      p.setLastName(player.getLastName());
      p.setComment(player.getComment());
      p.setIsChecked(player.getIsChecked());
      p.setStrength(player.getStrength());
      p.setPlayedSinglesTournaments(totalTournamentsPlayed);
      return playerRepository.save(p);
    }
  }

  public Set<Player> findAllByIsChecked(boolean b) {
    return playerRepository.findByIsChecked(true);
  }

  public Map<Integer, Set<Player>> groupPlayers(int groupSize) {

    // TODO: do not tak all the players out there, only those assigned to the tournament
    Set<Player> players =
        getAllPlayers().stream().filter(p -> p.getId() != -1).collect(Collectors.toSet());

    GrouperInterface playerGrouper = new PlayerGrouper(players, groupSize);

    Map<Integer, Set<Player>> groups = playerGrouper.groupPlayers();

    ////////////////////////////////////

    System.out.println("----------------------------------------------------");

    groups.entrySet().forEach(entry -> {
              GroupInSingles dummy = new GroupInSingles(entry.getKey(), entry.getValue());
              GroupInSingles newGroup = groupRepository.save(dummy);
              for (Player p : new HashSet<>(newGroup.getMembers())) {
                p.joinGroup(newGroup);
                playerRepository.save(p); // NO, DO THIS THROUGH SERVICE IN CASE IT ALREADY EXISTS
              }
            }
    );

    System.out.println("----------------------------------------------------");

    // tournament add groups
    // run all through DTOs
    // repeat for teams

    ///////////////////////////////////

    return groups;
  }

  public Map<Long, Boolean> checkPlayers(Map<Long, Boolean> idToCheckStatusMapping)
      throws Exception {

    Map<Long, Boolean> outcome = new HashMap<>();

    for (Long id : idToCheckStatusMapping.keySet()) {
      try {
        Player player =
            playerRepository
                .findById(id)
                .orElseThrow(
                    () -> new Exception("Batch check failed, player not found by id: " + id));
        player.setIsChecked(idToCheckStatusMapping.get(id));
        Player updatedPlayer = playerRepository.save(player);
        outcome.put(updatedPlayer.getId(), updatedPlayer.getIsChecked());

      } catch (Exception e) {
        e.printStackTrace();
        throw new Exception("Rethrowing: Batch check failed, player not found by id: " + id);
      }
    }
    return outcome;
  }
}
