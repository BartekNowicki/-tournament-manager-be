package com.example.tmbe.service;

import com.example.tmbe.dataModel.*;
import com.example.tmbe.dto.PlayerDTO;
import com.example.tmbe.repository.*;

import com.example.tmbe.utils.PlayerDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerService {
  @Autowired PlayerRepository playerRepository;
  @Autowired TournamentRepository tournamentRepository;

  public Set<PlayerDTO> getAllPlayers() {
    return playerRepository.findAll().stream().map(PlayerDTOMapper::toPlayerDTO).collect(Collectors.toSet());
  }

  public Optional<Player> getPlayerById(Long id) {
    return playerRepository.findById(id);
  }
}
