package com.app.tmbe.dto;

import com.app.tmbe.dataModel.DoublesTournament;
import com.app.tmbe.dataModel.SinglesTournament;
import com.app.tmbe.dataModel.Tournament;
import org.springframework.stereotype.Component;

@Component
public class TournamentDTOMapper {
  public static TournamentDTO toTournamentDTO(Tournament tournament) {
    if (tournament instanceof SinglesTournament) {
      return new SinglesTournamentDTO(
          tournament.getId(),
          tournament.getType(),
          tournament.getStartDate(),
          tournament.getEndDate(),
          tournament.getGroupSize(),
          tournament.getComment(),
          ((SinglesTournament) tournament).getParticipatingPlayers());
    } else if (tournament instanceof DoublesTournament) {
      return new DoublesTournamentDTO(
          tournament.getId(),
          tournament.getType(),
          tournament.getStartDate(),
          tournament.getEndDate(),
          tournament.getGroupSize(),
          tournament.getComment(),
          ((DoublesTournament) tournament).getParticipatingTeams());
    }
    return TournamentDTO.badTournamentDTO("something went wrong in the tournament DTO mapper");
  }
}
