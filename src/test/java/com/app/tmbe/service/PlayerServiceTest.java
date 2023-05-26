package com.app.tmbe.service;

import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.Tournament;
import com.app.tmbe.exception.NoEntityFoundCustomException;
import com.app.tmbe.enumConverter.TournamentType;
import com.app.tmbe.repository.PlayerRepository;
import com.app.tmbe.repository.TournamentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

  @InjectMocks PlayerService playerService;
  @Mock PlayerRepository playerRepository;
  @Mock TournamentRepository tournamentRepository;
  private final List<Player> list = new ArrayList<>();

  @BeforeEach
  void setUp() {

    Player player1 =
        new Player(1L, true, "Joe", "Doe", 10, "Joe Doe is a great player", new HashSet<>());
    Player player2 =
        new Player(2L, true, "Jack", "Schmoe", 9, "Jack Schmoe is a puny player", new HashSet<>());
    Player player3 = new Player(3L, false, "Moe", "Broe", 9, "Moe cannot play", new HashSet<>());

    list.add(player2);
    list.add(player3);
    list.add(player1);
  }

  @AfterEach
  void tearDown() {}

  @Test
  void getAllPlayersOrderByIdAsc_success() {

    // given
    List<Player> sortedList =
        list.stream().sorted(Comparator.comparingLong(Player::getId)).collect(Collectors.toList());

    // when
    when(playerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(sortedList);
    List<Player> players = playerService.getAllPlayersOrderByIdAsc();

    // then
    assertEquals(3, players.size());
    assertEquals(1, players.get(0).getId());
    assertEquals(2, players.get(1).getId());
    assertEquals(3, players.get(2).getId());
    verify(playerRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "id"));
  }

  @Test
  void getPlayerById_success() {

    // given

    // when
    when(playerRepository.findById(1L)).thenReturn(Optional.of(list.get(2)));
    Player player = playerService.getPlayerById(1L).get();
    Optional<Player> emptyOptional = playerService.getPlayerById(99L);

    // then
    assertEquals(1L, player.getId());
    assertFalse(emptyOptional.isPresent());
    verify(playerRepository, times(2)).findById(any());
  }

  @Test
  void deletePlayerById_success() throws NoEntityFoundCustomException {
    // given
    Player player =
        new Player(10L, true, "Joe", "Doe", 10, "Joe Doe is a great player", new HashSet<>());
    Tournament tournament =
        new Tournament(
            20L,
            TournamentType.DOUBLES,
            new Date(),
            new Date(),
            1,
            "the tournament was awesome",
            new HashSet<>());

    Set<Tournament> playedTournaments = new HashSet<Tournament>();
    Set<Player> participatingPlayers = new HashSet<Player>();

    playedTournaments.add(tournament);
    participatingPlayers.add(player);

    player.setPlayedTournaments(playedTournaments);
    tournament.setParticipatingPlayers(participatingPlayers);

    // when
    when(playerRepository.findById(1L)).thenReturn(Optional.ofNullable(player));
    Player deletedPlayer = playerService.deletePlayerById(1L);

    // then
    assertEquals(10L, deletedPlayer.getId());
    assertEquals(0L, participatingPlayers.size());
    assertEquals(0L, playedTournaments.size());

    verify(playerRepository, times(1)).findById(1L);
  }

  @Test
  void saveOrUpdatePlayer_success() {}

  @Test
  void findAllByIsChecked_success() {

    // given

    // when
    when(playerRepository.findByIsChecked(true)).thenReturn(Set.of(list.get(0)));
    Set<Player> playersChecked = playerService.findAllByIsChecked(true);
    Set<Player> playersUnChecked = playerService.findAllByIsChecked(false);

    // then
    assertEquals(1, playersChecked.size());
    assertEquals(1, playersUnChecked.size());
    verify(playerRepository, times(2)).findByIsChecked(anyBoolean());
  }
}
