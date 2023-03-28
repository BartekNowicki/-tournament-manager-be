package com.example.tmbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlayerDTO {
  private long playerId;
  private boolean isChecked;
  private String firstName;
  private String lastName;
  private int strength;
  private String comment;
}
