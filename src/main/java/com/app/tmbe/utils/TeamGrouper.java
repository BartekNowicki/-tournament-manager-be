package com.app.tmbe.utils;

import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TeamGrouper implements GrouperInterface {
  Set<Team> teams;
  int groupSize;

  public TeamGrouper(Set<Team> teams, int groupSize) {
    this.teams = teams;
    this.groupSize = groupSize;
  }

  @Override
  public Map<Integer, Set<Team>> groupTeams() {
    List<Team> teamsToGroup = new ArrayList<>(teams);
    Collections.shuffle(new ArrayList<>(teamsToGroup)); // this is done in place, returns void
    int groupCount = teams.size() / groupSize;
    if (isLastGroupNotFull(groupCount)) {
      groupCount++;
    }

    Map<Integer, Set<Team>> groups = new HashMap<>();
    for (int i = 1; i <= groupCount; i++) {
      groups.put(
          i,
          Set.copyOf(
              teamsToGroup.subList(groupSize * (i - 1), Math.min(groupSize * i, teams.size()))));
    }

    return groups;
  }

  private boolean isLastGroupNotFull(int groupCount) {
    return teams.size() % groupSize > 1;
  }

  @Override
  public Map<Integer, Set<Player>> groupPlayers() {
    return null;
  }
}
