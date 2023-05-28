package com.app.tmbe.dto;

import com.app.tmbe.dataModel.Player;
import com.app.tmbe.enumConverter.TournamentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

// @AllArgsConstructor
// lombok does not deal well with inheritance, normal constructor implemented below
@Getter
public class SinglesTournamentDTO extends TournamentDTO {

  private Set<Player> participatingPlayers;

  public SinglesTournamentDTO(
      long id,
      TournamentType type,
      Date startDate,
      Date endDate,
      int groupSize,
      String comment,
      Set<Player> participatingPlayers) {
    super(id, type, startDate, endDate, groupSize, comment);
  }

  public static SinglesTournamentDTO badTournamentDTO(String message) {
    return new SinglesTournamentDTO(0, null, null, null, 0, message, Set.of());
  }
}
