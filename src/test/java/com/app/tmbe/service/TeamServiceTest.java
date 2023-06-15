package com.app.tmbe.service;

import com.app.tmbe.dataModel.DoublesTournament;
import com.app.tmbe.dataModel.GroupInDoubles;
import com.app.tmbe.dataModel.GroupInSingles;
import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.SinglesTournament;
import com.app.tmbe.dataModel.Team;
import com.app.tmbe.enumConverter.TournamentType;
import com.app.tmbe.repository.DoublesTournamentRepository;
import com.app.tmbe.repository.SinglesTournamentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {
  @InjectMocks TeamService teamService;
  @Mock DoublesTournamentRepository doublesTournamentRepository;

  @Test
  void getAllTeams() {}

  @Test
  void getAllTeamsOrderByIdAsc() {}

  @Test
  void getTeamById() {}

  @Test
  void deleteTeamById() {}

  @Test
  void saveOrUpdateTeam() {}

  @Test
  void findAllByIsChecked() {}

  @Test
  void checkTeams() {}

  @Test
  void groupTeams_failure_whenAlreadyGrouped() throws Exception {

    // given
    Team joesTeam = new Team(1L, true, 1, 2, 1, "JoesTeam", Set.of(), Set.of());

    DoublesTournament tournament = new DoublesTournament();
    Set<GroupInDoubles> groups =
        Set.of(new GroupInDoubles(1L, Set.of(joesTeam), new DoublesTournament()));

    DoublesTournament tournamentUnderTest =
        new DoublesTournament(
            1L,
            TournamentType.DOUBLES,
            Date.from(Instant.now()),
            Date.from(Instant.now()),
            10,
            "",
            Set.of(joesTeam));
    tournamentUnderTest.setGroups(groups);

    // when
    when(doublesTournamentRepository.findById(anyLong()))
        .thenReturn(Optional.of(tournamentUnderTest));

    // then
    Exception exception =
        assertThrows(
            Exception.class,
            () -> {
              teamService.groupTeams(1L);
            });
    assertTrue(
        exception.getMessage().contains("This tournament already has groups assigned to it!"));
  }
}
