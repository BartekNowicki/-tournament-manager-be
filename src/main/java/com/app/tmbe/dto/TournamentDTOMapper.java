package com.app.tmbe.dto;

import com.app.tmbe.dataModel.Tournament;
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
        tournament.getComment(),
        tournament.getParticipatingPlayers());
  }
}
