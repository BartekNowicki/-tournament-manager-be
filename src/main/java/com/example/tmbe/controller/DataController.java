package com.example.tmbe.controller;

import com.example.tmbe.dataModel.Player;
import com.example.tmbe.dataModel.Tournament;
import com.example.tmbe.dto.*;
import com.example.tmbe.service.PlayerService;
import com.example.tmbe.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// @CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(origins = "http://localhost:5174")
@Controller
@RequestMapping("/api/data")
public class DataController {

  @Autowired private PlayerService playerService;
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

  @GetMapping("/tournaments")
  public ResponseEntity<List<TournamentDTO>> getAllTournamentsOrderByIdAsc() {

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

  @GetMapping("/tournaments/{id}")
  public ResponseEntity<TournamentDTO> getTournament(@PathVariable("id") long id) {

    try {
      Optional<TournamentDTO> tournament =
          tournamentService.getTournamentById(id).map(TournamentDTOMapper::toTournamentDTO);

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

  @DeleteMapping("/tournaments/{id}")
  public ResponseEntity<TournamentDTO> deleteTournament(@PathVariable("id") long id) {

    try {

      TournamentDTO deletedTournament =
          TournamentDTOMapper.toTournamentDTO(tournamentService.deleteTournamentById(id));

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

  @PutMapping("/tournaments")
  public ResponseEntity<TournamentDTO> saveOrUpdateTournament(@RequestBody Tournament tournament) {

    try {

      TournamentDTO savedOrUpdatedTournament =
          TournamentDTOMapper.toTournamentDTO(tournamentService.saveOrUpdateTournament(tournament));

      return new ResponseEntity<>(savedOrUpdatedTournament, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
