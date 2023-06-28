package com.app.tmbe.dataModel;

import com.app.tmbe.enumConverter.TournamentType;
import lombok.Getter;
import java.util.Date;
import java.util.Set;

@Getter
public class TournamentRequestEntity {
  private long id;
  private TournamentType type;
  private Date startDate;
  private Date endDate;
  private int groupSize;
  private String comment;
  //  private Set<Long> participatingPlayers;
  //  private Set<Long> participatingTeams;
  //  private Set<Long> groupsInSingles;
  //  private Set<Long> groupsInDoubles;
  //  private Set<Long> groups;
}
