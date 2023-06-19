package com.app.tmbe.dto;

import com.app.tmbe.enumConverter.TournamentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@Getter
public class TournamentDTO {
  private long id;
  private TournamentType type;
  private Date startDate;
  private Date endDate;
  private int groupSize;
  private String comment;

  private Set<Long> participatingPlayers;
  private Set<Long> participatingTeams;

  public static TournamentDTO badTournamentDTO(String message) {
    return new TournamentDTO(0, null, null, null, 0, message, null, null);
  }
}
