package com.example.tmbe.repository;

import com.example.tmbe.config.AppConfig;
import com.example.tmbe.dataModel.Player;
import com.example.tmbe.dataModel.Tournament;
import com.example.tmbe.enumConverter.TournamentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.ws.rs.core.Application;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@SpringBootTest(classes = Application.class)
public class SpringBootJPA_H2_IntegrationTest {

  Player player1 = new Player(1L, true, "Joe", "Doe", 10, "Joe Doe is a great player", Set.of());

  Player player2 =
      new Player(2L, true, "Jack", "Schmoe", 9, "Jack Schmoe is a puny player", Set.of());

  Tournament tournament1 =
      new Tournament(
          1L,
          TournamentType.SINGLES,
          new Date(),
          new Date(),
          3,
          "tournament1 was awesome",
          Set.of(player1, player2));
  Tournament tournament2 =
      new Tournament(
          2L, TournamentType.DOUBLES, new Date(), new Date(), 6, "tournament1 was awesome", Set.of(player1));

  @Autowired private PlayerRepository playerRepository;
  @Autowired private TournamentRepository tournamentRepository;

  @BeforeEach
  void setUp() {
    playerRepository.save(player1);
    playerRepository.save(player2);
    tournamentRepository.save(tournament1);
    tournamentRepository.save(tournament2);
  }

  @Test
  public void givenTesterRepository_SaveAndRetreiveEntity_success() {

    Optional<Player> pl1 = playerRepository.findById(1L);
    Optional<Player> pl2 = playerRepository.findById(2L);
    Optional<Tournament> t1 = tournamentRepository.findById(1L);
    Optional<Tournament> t2 = tournamentRepository.findById(2L);

    assertNotNull(pl1);
    assertNotNull(pl2);
    assertNotNull(t1);
    assertNotNull(t2);
    assertEquals(player1.getFirstName(), pl1.get().getFirstName());
    assertEquals(tournament1.getGroupSize(), t1.get().getGroupSize());
    assertEquals(tournament2.getGroupSize(), t2.get().getGroupSize());
  }

  //  @Test
  //  void should_retrieve_all_users() throws Exception {
  //    this.mockMvc.perform(get("/user/fetchAll"))
  //            .andDo(print())
  //            .andExpect(status().isOk())
  //            .andExpect(content().contentType(APPLICATION_JSON))
  //            .andExpect(jsonPath("$").isArray())
  //            .andExpect(jsonPath("$", hasSize(5)))
  //            .andExpect(jsonPath("$.[0].id").value(1))
  //            .andExpect(jsonPath("$.[1].id").value(2))
  //            .andExpect(jsonPath("$.[2].id").value(3))
  //            .andExpect(jsonPath("$.[3].id").value(4))
  //            .andExpect(jsonPath("$.[4].id").value(5));
  //  }
}
