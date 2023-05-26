package com.app.tmbe.service;

import java.io.IOException;
import java.util.List;

import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.Tournament;
import com.app.tmbe.repository.PlayerRepository;
import com.app.tmbe.repository.TournamentRepository;
import com.app.tmbe.utils.CSVHelper;
import com.app.tmbe.dataModel.*;
import com.app.tmbe.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {

  @Autowired
  PlayerRepository playerRepository;
  @Autowired
  TournamentRepository tournamentRepository;

  public void savePlayers(MultipartFile file) {
    try {
      List<Player> players = CSVHelper.csvToPlayers(file.getInputStream());
      playerRepository.saveAll(players);
    } catch (IOException e) {
      throw new RuntimeException("failed to save csv data: " + e.getMessage());
    }
  }

  public void saveTournaments(MultipartFile file) {
    try {
      List<Tournament> tournaments = CSVHelper.csvToTournaments(file.getInputStream());
      tournamentRepository.saveAll(tournaments);
    } catch (IOException e) {
      throw new RuntimeException("failed to save csv data: " + e.getMessage());
    }
  }
}
