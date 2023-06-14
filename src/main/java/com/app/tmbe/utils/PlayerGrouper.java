package com.app.tmbe.utils;

import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlayerGrouper implements GrouperInterface {
  Set<Player> players;
  int groupSize;

  public PlayerGrouper(Set<Player> players, int groupSize) {
    this.players = players;
    this.groupSize = groupSize;
  }

  @Override
  public Map<Integer, Set<Player>> groupPlayers() {
    List<Player> playersToGroup = new ArrayList<>(players);
    Collections.shuffle(new ArrayList<>(playersToGroup)); // this is done in place, returns void
    int groupCount = players.size() / groupSize;
    if (isLastGroupNotFull()) {
      groupCount++;
    }

    Map<Integer, Set<Player>> groups = new HashMap<>();
    for (int i = 1; i <= groupCount; i++) {
      groups.put(
          i,
          Set.copyOf(
              playersToGroup.subList(
                  groupSize * (i - 1), Math.min(groupSize * i, players.size()))));
    }

    return groups;
  }

  private boolean isLastGroupNotFull() {
    return players.size() % groupSize > 0;
  }

  @Override
  public Map<Integer, Set<Team>> groupTeams() {
    return null;
  }
}
