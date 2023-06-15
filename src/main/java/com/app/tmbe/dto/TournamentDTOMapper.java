package com.app.tmbe.dto;

import com.app.tmbe.dataModel.DoublesTournament;
import com.app.tmbe.dataModel.SinglesTournament;
import com.app.tmbe.dataModel.Tournament;
import org.springframework.stereotype.Component;

import java.util.Set;

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
          ((SinglesTournament) tournament).getParticipatingPlayers(),
          Set.of(),
          ((SinglesTournament) tournament).getGroups());

    } else if (tournament instanceof DoublesTournament) {
      return new DoublesTournamentDTO(
          tournament.getId(),
          tournament.getType(),
          tournament.getStartDate(),
          tournament.getEndDate(),
          tournament.getGroupSize(),
          tournament.getComment(),
          Set.of(),
          ((DoublesTournament) tournament).getParticipatingTeams(),
          ((DoublesTournament) tournament).getGroups());
    }
    return TournamentDTO.badTournamentDTO("something went wrong in the tournament DTO mapper");
  }
}
