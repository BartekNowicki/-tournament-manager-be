package com.app.tmbe.dto;

import com.app.tmbe.dataModel.Tournament;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class PlayerDTO {
  private long id;
  private boolean isChecked;
  private String firstName;
  private String lastName;
  private int strength;
  private String comment;
  private Set<Tournament> playedTournaments;

  public static PlayerDTO badPlayerDTO(String message) {
    return new PlayerDTO(0, false, null, null, 0, message, Set.of());
  }
}