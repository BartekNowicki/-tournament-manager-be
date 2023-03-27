package com.example.tmbe.service;

import com.example.tmbe.dataModel.Tournament;
import com.example.tmbe.dto.TournamentDTO;
import com.example.tmbe.repository.TournamentRepository;
import com.example.tmbe.utils.TournamentDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TournamentService {
  @Autowired TournamentRepository tournamentRepository;

  public Set<TournamentDTO> getAllTournaments() {
    return tournamentRepository.findAll().stream()
        .map(TournamentDTOMapper::toTournamentDTO)
        .collect(Collectors.toSet());
  }

  public Optional<Tournament> getTournamentById(Long id) {
    return tournamentRepository.findById(id);
  }
}
