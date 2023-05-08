package com.example.tmbe.dto;

import com.example.tmbe.dataModel.Tournament;
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

  public static PlayerDTO badPlayerDTO(String message) {
    return new PlayerDTO(0, false, null, null, 0, message);
  }
}
