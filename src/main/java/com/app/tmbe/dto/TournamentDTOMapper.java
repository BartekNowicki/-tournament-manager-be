package com.app.tmbe.dto;

import com.app.tmbe.dataModel.DoublesTournament;
import com.app.tmbe.dataModel.SinglesTournament;
import com.app.tmbe.dataModel.Tournament;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

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
          ((SinglesTournament) tournament)
              .getParticipatingPlayers().stream()
                  .map(player -> player.getId())
                  .collect(Collectors.toSet()),
          Set.of(),
          ((SinglesTournament) tournament).getGroups().stream()
                  .map(group -> group.getId())
                  .collect(Collectors.toSet()));

    } else if (tournament instanceof DoublesTournament) {
      return new DoublesTournamentDTO(
          tournament.getId(),
          tournament.getType(),
          tournament.getStartDate(),
          tournament.getEndDate(),
          tournament.getGroupSize(),
          tournament.getComment(),
          Set.of(),
          ((DoublesTournament) tournament)
              .getParticipatingTeams().stream()
                  .map(team -> team.getId())
                  .collect(Collectors.toSet()),
          ((DoublesTournament) tournament).getGroups().stream()
                  .map(group -> group.getId())
                  .collect(Collectors.toSet()));
    }
    return TournamentDTO.badTournamentDTO("something went wrong in the tournament DTO mapper");
  }
}
