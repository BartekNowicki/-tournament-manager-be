package com.app.tmbe.utils;

import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamGrouperTest {

  private final Set<Team> teams = new HashSet<>();
  GrouperInterface teamGrouper;

  @BeforeEach
  void setUp() {
    IntStream.range(1, 11)
        .forEach(
            i ->
                teams.add(
                    new Team(
                        i,
                        true,
                        0 + i,
                        100 + i,
                        i,
                        "I was " + i,
                        new HashSet<>(),
                        new HashSet<>())));
  }

  @Test
  void groupPlayers_success() {

    // given

    // when
    teamGrouper = new TeamGrouper(teams, 3);
    Map<Integer, Set<Team>> groups = teamGrouper.groupTeams();
    System.out.println(teams);

    // then
    assertEquals(10, teams.size());
    assertEquals(4, groups.size());
    assertEquals(1, groups.get(4).size());
  }
}
