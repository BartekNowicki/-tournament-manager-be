package com.app.tmbe.dto;

import com.app.tmbe.dataModel.DoublesTournament;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class TeamDTO {
  private long id;
  private boolean isChecked;
  private long playerOneId;
  private long playerTwoId;
  private int strength;
  private String comment;
  private Set<DoublesTournament> playedDoublesTournaments;

  public static TeamDTO badTeamDTO(String message) {
    return new TeamDTO(0, false, -1, -1, 0, message, Set.of());
  }
}
