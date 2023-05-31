package com.app.tmbe.dataModel;

import com.app.tmbe.enumConverter.TournamentType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
// lombok does not deal well with inheritance, normal constructor implemented below
// @AllArgsConstructor
@Table(name = "doubles_tournaments")
public class DoublesTournament extends Tournament {

  // Bidirectional @ManyToMany, two parents, no children, one owner (Player)
  @JsonBackReference
  @ManyToMany(mappedBy = "playedDoublesTournaments")
  private Set<Team> participatingTeams = new HashSet<>();

  public DoublesTournament(
      long id,
      TournamentType type,
      Date startDate,
      Date endDate,
      int groupSize,
      String comment,
      Set<Team> participatingTeams) {
    super(id, type, startDate, endDate, groupSize, comment);
    this.participatingTeams = participatingTeams;
  }

//  public DoublesTournament(Set<Team> participatingTeams) {
//    this.participatingTeams = participatingTeams;
//  }

  public void addTeam(Team team) {
    this.participatingTeams.add(team);
    team.getPlayedDoublesTournaments().add(this);
  }

  public void removeTeam(Team team) {
    this.participatingTeams.remove(team);
    team.getPlayedDoublesTournaments().remove(this);
  }

  @Override
  public String toString() {
    return "DoublesTournament{"
        + "id="
        + super.getId()
        + ", type='"
        + super.getType()
        + '\''
        + ", startDate="
        + super.getStartDate()
        + ", endDate="
        + super.getEndDate()
        + ", groupSize="
        + super.getGroupSize()
        + ", comment='"
        + super.getComment()
        + '\''
        + ", teams="
        + participatingTeams.size()
        + '}';
  }
}
