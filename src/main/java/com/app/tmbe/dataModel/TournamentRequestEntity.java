package com.app.tmbe.dataModel;

import com.app.tmbe.enumConverter.TournamentType;
import lombok.Getter;
import java.util.Date;

@Getter
public class TournamentRequestEntity {
  private long id;
  private TournamentType type;
  private Date startDate;
  private Date endDate;
  private int groupSize;
  private String comment;
}
