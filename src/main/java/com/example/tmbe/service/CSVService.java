package com.example.tmbe.service;

import java.io.IOException;
import java.util.List;

import com.example.tmbe.dataModel.*;
import com.example.tmbe.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.tmbe.utils.CSVHelper;

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
