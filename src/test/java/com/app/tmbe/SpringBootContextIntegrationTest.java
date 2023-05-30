package com.app.tmbe;

import com.app.tmbe.config.AppConfig;

import com.app.tmbe.controller.DataController;
import com.app.tmbe.repository.PlayerRepository;
import com.app.tmbe.repository.SinglesTournamentRepository;
import com.app.tmbe.service.PlayerService;
import com.app.tmbe.service.TournamentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class SpringBootContextIntegrationTest {

  @Autowired private PlayerRepository playerRepository;

  @Autowired private SinglesTournamentRepository singlesTournamentRepository;

  @Autowired private PlayerService playerService;

  @Autowired private TournamentService tournamentService;

  @Autowired private DataController controller;

  @Test
  public void whenContextLoads_thenPlayerRepositoryIsNotNull() {
    assertThat(playerRepository).isNotNull();
  }

  @Test
  public void whenContextLoads_thenTournamentRepositoryIsNotNull() {
    assertThat(singlesTournamentRepository).isNotNull();
  }

  @Test
  public void whenContextLoads_thenPlayerServiceIsNotNull() {
    assertThat(playerService).isNotNull();
  }

  @Test
  public void whenContextLoads_thenTournamentServiceIsNotNull() {
    assertThat(tournamentService).isNotNull();
  }

  @Test
  public void whenContextLoads_thenDataControllerIsNotNull() {
    assertThat(controller).isNotNull();
  }
}
