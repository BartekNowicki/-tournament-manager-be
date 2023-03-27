package com.example.tmbe.utils;

import com.example.tmbe.dataModel.Tournament;
import com.example.tmbe.dto.PlayerDTO;
import com.example.tmbe.dto.TournamentDTO;
import org.springframework.stereotype.Component;

@Component
public class TournamentDTOMapper {
  public static TournamentDTO toTournamentDTO(Tournament tournament) {
    return new TournamentDTO(
        tournament.getId(),
        tournament.getType(),
        tournament.getStartDate(),
        tournament.getEndDate(),
        tournament.getGroupSize(),
        tournament.getComment());
  }
}
