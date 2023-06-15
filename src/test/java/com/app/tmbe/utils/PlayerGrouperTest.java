package com.app.tmbe.utils;

import com.app.tmbe.dataModel.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlayerGrouperTest {

  private final Set<Player> players = new HashSet<>();
  GrouperInterface playerGrouper;

  @BeforeEach
  void setUp() {
    IntStream.range(1, 11)
        .forEach(
            i ->
                players.add(
                    new Player(
                        i,
                        true,
                        "Joe" + i,
                        "Doe" + i,
                        i,
                        "I was " + i,
                        new HashSet<>(),
                        new HashSet<>())));
  }

  @Test
  void groupPlayers_success() {

    // given

    // when
    playerGrouper = new PlayerGrouper(players, 3);
    Map<Integer, Set<Player>> groups = playerGrouper.groupPlayers();

    // then
    assertEquals(10, players.size());
    assertEquals(4, groups.size());
    assertEquals(1, groups.get(4).size());
  }
}
