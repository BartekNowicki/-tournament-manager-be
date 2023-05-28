package com.app.tmbe.dto;

import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.Team;
import com.app.tmbe.dataModel.Tournament;
import com.app.tmbe.enumConverter.TournamentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

// @AllArgsConstructor
// lombok does not deal well with inheritance, normal constructor implemented below
@Getter
public class DoublesTournamentDTO extends TournamentDTO {
  private Set<Team> participatingTeams;

  public DoublesTournamentDTO(
      long id,
      TournamentType type,
      Date startDate,
      Date endDate,
      int groupSize,
      String comment,
      Set<Team> participatingTeams) {
    super(id, type, startDate, endDate, groupSize, comment);
  }

  public static DoublesTournamentDTO badTournamentDTO(String message) {
    return new DoublesTournamentDTO(0, null, null, null, 0, message, Set.of());
  }
}
