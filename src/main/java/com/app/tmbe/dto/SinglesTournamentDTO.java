package com.app.tmbe.dto;

import com.app.tmbe.dataModel.GroupInSingles;
import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.Team;
import com.app.tmbe.enumConverter.TournamentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

// @AllArgsConstructor
// lombok does not deal well with inheritance, normal constructor implemented below
@Getter
public class SinglesTournamentDTO extends TournamentDTO {

  public SinglesTournamentDTO(
      long id,
      TournamentType type,
      Date startDate,
      Date endDate,
      int groupSize,
      String comment,
      Set<Long> participatingPlayers,
      Set<Long> participatingTeams,
      Set<GroupInSingles> groups) {
    super(
        id, type, startDate, endDate, groupSize, comment, participatingPlayers, participatingTeams);
  }

  public static SinglesTournamentDTO badTournamentDTO(String message) {
    return new SinglesTournamentDTO(0, null, null, null, 0, message, Set.of(), Set.of(), Set.of());
  }
}
