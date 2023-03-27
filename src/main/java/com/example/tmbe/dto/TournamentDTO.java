package com.example.tmbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class TournamentDTO {
  private long id;
  private String type;
  private Date startDate;
  private Date endDate;
  private int groupSize;
  private String comment;
}
