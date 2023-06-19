package com.app.tmbe.controller;

import com.app.tmbe.dataModel.DoublesTournament;
import com.app.tmbe.dataModel.SinglesTournament;
import com.app.tmbe.dataModel.TournamentRequestEntity;
import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.Team;
import com.app.tmbe.dto.PlayerDTO;
import com.app.tmbe.dto.PlayerDTOMapper;
import com.app.tmbe.dto.TeamDTO;
import com.app.tmbe.dto.TeamDTOMapper;
import com.app.tmbe.dto.TournamentDTO;
import com.app.tmbe.dto.TournamentDTOMapper;
import com.app.tmbe.enumConverter.TournamentType;
import com.app.tmbe.service.TeamService;
import com.app.tmbe.service.TournamentService;
import com.app.tmbe.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequestMapping("/api/data")
public class DataController {

  @Autowired private PlayerService playerService;
  @Autowired private TeamService teamService;
  @Autowired private TournamentService tournamentService;

  @GetMapping("/players")
  public ResponseEntity<List<PlayerDTO>> getAllPlayersOrderByIdAsc() {

    try {
      List<PlayerDTO> players =
          playerService.getAllPlayersOrderByIdAsc().stream()
              .map(PlayerDTOMapper::toPlayerDTO)
              .collect(Collectors.toList());

      if (players.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(players, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/players/grouped/{singlesTournamentId}")
  public ResponseEntity<Map<Integer, Set<Player>>> getGroupedPlayers(
      @PathVariable("singlesTournamentId") long singlesTournamentId) {

    try {

      Map<Integer, Set<Player>> players = playerService.groupPlayers(singlesTournamentId);

      Map<Integer, Set<Player>> playersMappedToDTO =
          players.entrySet().stream()
              .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
      // TODO: map key to DTO not to itself

      if (players.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(playersMappedToDTO, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/teams/grouped/{doublesTournamentId}")
  public ResponseEntity<Map<Integer, Set<Team>>> getGroupedTeams(
      @PathVariable("doublesTournamentId") long doublesTournamentId) {

    try {

      Map<Integer, Set<Team>> teams = teamService.groupTeams(doublesTournamentId);

      Map<Integer, Set<Team>> teamsMappedToDTO =
          teams.entrySet().stream()
              .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
      // TODO: map key to DTO not to itself

      if (teams.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(teamsMappedToDTO, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/players/{id}")
  public ResponseEntity<PlayerDTO> getPlayer(@PathVariable("id") long id) {

    try {
      Optional<PlayerDTO> player =
          playerService.getPlayerById(id).map(PlayerDTOMapper::toPlayerDTO);

      if (player.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(player.get(), HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/teams")
  public ResponseEntity<List<TeamDTO>> getAllTeamsOrderByIdAsc() {

    try {
      List<TeamDTO> teams =
          teamService.getAllTeamsOrderByIdAsc().stream()
              .map(TeamDTOMapper::toTeamDTO)
              .collect(Collectors.toList());

      if (teams.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(teams, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/teams/{id}")
  public ResponseEntity<TeamDTO> getTeam(@PathVariable("id") long id) {

    try {
      Optional<TeamDTO> team = teamService.getTeamById(id).map(TeamDTOMapper::toTeamDTO);

      if (team.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(team.get(), HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tournaments")
  public ResponseEntity<List<? extends TournamentDTO>> getAllTournamentsOrderByIdAsc() {

    try {
      List<TournamentDTO> tournaments =
          tournamentService.getAllTournamentsOrderByIdAsc().stream()
              .map(TournamentDTOMapper::toTournamentDTO)
              .collect(Collectors.toList());

      if (tournaments.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tournaments, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tournaments/singles/{id}")
  public ResponseEntity<? extends TournamentDTO> getSinglesTournament(@PathVariable("id") long id) {

    try {
      Optional<TournamentDTO> tournament =
          tournamentService.getSinglesTournamentById(id).map(TournamentDTOMapper::toTournamentDTO);

      if (tournament.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tournament.get(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tournaments/doubles/{id}")
  public ResponseEntity<? extends TournamentDTO> getDoublesTournament(@PathVariable("id") long id) {

    try {
      Optional<TournamentDTO> tournament =
          tournamentService.getDoublesTournamentById(id).map(TournamentDTOMapper::toTournamentDTO);

      if (tournament.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tournament.get(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/players/{id}")
  public ResponseEntity<PlayerDTO> deletePlayer(@PathVariable("id") long id) {

    try {
      PlayerDTO deletedPlayer = PlayerDTOMapper.toPlayerDTO(playerService.deletePlayerById(id));

      return new ResponseEntity<>(deletedPlayer, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      // return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(PlayerDTO.badPlayerDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/teams/{id}")
  public ResponseEntity<TeamDTO> deleteTeam(@PathVariable("id") long id) {

    try {
      TeamDTO deletedTeam = TeamDTOMapper.toTeamDTO(teamService.deleteTeamById(id));

      return new ResponseEntity<>(deletedTeam, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      // return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(TeamDTO.badTeamDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/tournaments/singles/{id}")
  public ResponseEntity<? extends TournamentDTO> deleteSinglesTournament(
      @PathVariable("id") long id) {

    try {

      TournamentDTO deletedTournament =
          TournamentDTOMapper.toTournamentDTO(tournamentService.deleteSinglesTournamentById(id));

      return new ResponseEntity<>(deletedTournament, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      return new ResponseEntity<>(
          TournamentDTO.badTournamentDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/tournaments/doubles/{id}")
  public ResponseEntity<? extends TournamentDTO> deleteDoublesTournament(
      @PathVariable("id") long id) {

    try {

      TournamentDTO deletedTournament =
          TournamentDTOMapper.toTournamentDTO(tournamentService.deleteDoublesTournamentById(id));

      return new ResponseEntity<>(deletedTournament, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      return new ResponseEntity<>(
          TournamentDTO.badTournamentDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/players")
  public ResponseEntity<PlayerDTO> saveOrUpdatePlayer(@RequestBody Player player) {

    try {

      PlayerDTO savedOrUpdatedPlayer =
          PlayerDTOMapper.toPlayerDTO(playerService.saveOrUpdatePlayer(player));

      return new ResponseEntity<>(savedOrUpdatedPlayer, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/teams")
  public ResponseEntity<TeamDTO> saveOrUpdateTeam(@RequestBody Team team) {

    try {

      TeamDTO savedOrUpdatedTeam = TeamDTOMapper.toTeamDTO(teamService.saveOrUpdateTeam(team));

      return new ResponseEntity<>(savedOrUpdatedTeam, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PatchMapping("/teams")
  public ResponseEntity<Map<Long, Boolean>> checkTeams(
      @RequestBody Map<Long, Boolean> idToCheckStatusMapping) {

    try {

      Map<Long, Boolean> updatedTeamStatusByIdMapping =
          teamService.checkTeams(idToCheckStatusMapping);

      return new ResponseEntity<>(updatedTeamStatusByIdMapping, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PatchMapping("/players")
  public ResponseEntity<Map<Long, Boolean>> checkPlayers(
      @RequestBody Map<Long, Boolean> idToCheckStatusMapping) {

    try {

      Map<Long, Boolean> updatedPlayerStatusByIdMapping =
          playerService.checkPlayers(idToCheckStatusMapping);

      return new ResponseEntity<>(updatedPlayerStatusByIdMapping, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/tournaments")
  public ResponseEntity<? extends TournamentDTO> saveOrUpdateTournament(
      // @RequestBody SinglesTournament singlesTournament) {
      @RequestBody TournamentRequestEntity t) {

    if (t.getType() == TournamentType.SINGLES) {
      SinglesTournament tournament =
          new SinglesTournament(
              t.getId(),
              t.getType(),
              t.getStartDate(),
              t.getEndDate(),
              t.getGroupSize(),
              t.getComment(),
              t.getParticipatingPlayers());
      try {

        TournamentDTO savedOrUpdatedTournament =
            TournamentDTOMapper.toTournamentDTO(
                tournamentService.saveOrUpdateSinglesTournament(tournament));
        return new ResponseEntity<>(savedOrUpdatedTournament, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } else {
      DoublesTournament tournament =
          new DoublesTournament(
              t.getId(),
              t.getType(),
              t.getStartDate(),
              t.getEndDate(),
              t.getGroupSize(),
              t.getComment(),
              t.getParticipatingTeams());
      try {

        TournamentDTO savedOrUpdatedTournament =
            TournamentDTOMapper.toTournamentDTO(
                tournamentService.saveOrUpdateDoublesTournament(tournament));
        return new ResponseEntity<>(savedOrUpdatedTournament, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
  }

  //  @PostMapping("/tournaments/assignToSingles")
  @GetMapping("/tournaments/assignToSingles")
  public ResponseEntity<? extends TournamentDTO> assignPlayersToSinglesTournament(
      @RequestParam Long tournamentId) {

    try {

      TournamentDTO savedOrUpdatedTournament =
          TournamentDTOMapper.toTournamentDTO(
              tournamentService.assignPlayersToSinglesTournament(tournamentId));

      return new ResponseEntity<>(savedOrUpdatedTournament, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tournaments/assignToDoubles")
  public ResponseEntity<? extends TournamentDTO> assignTeamsToDoublesTournament(
      @RequestParam Long tournamentId) {

    try {

      TournamentDTO savedOrUpdatedTournament =
          TournamentDTOMapper.toTournamentDTO(
              tournamentService.assignTeamsToDoublesTournament(tournamentId));

      return new ResponseEntity<>(savedOrUpdatedTournament, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
