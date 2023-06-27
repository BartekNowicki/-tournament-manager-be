package com.app.tmbe.dataModel;

import com.app.tmbe.enumConverter.TournamentType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
  @JsonBackReference(value = "doublestournament-teams")
  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "playedDoublesTournaments")
  private Set<Team> participatingTeams = new HashSet<>();

  // Bidirectional @OneToMany, two parents, no children, one owner (GroupInDoubles)
  @JsonBackReference(value = "doublestournament-groups")
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "partOfDoublesTournament")
  private Set<GroupInDoubles> groups = new HashSet<>();

  @Override
  public String toString() {
    return "DoublesTournament{"
        + "participatingTeams="
        + participatingTeams
        + ", groups="
        + groups
        + "} "
        + super.toString();
  }

  public DoublesTournament(
      long id,
      TournamentType type,
      Date startDate,
      Date endDate,
      int groupSize,
      String comment,
      Set<Team> participatingTeams,
      Set<GroupInDoubles> groups) {
    super(id, type, startDate, endDate, groupSize, comment);
    this.participatingTeams = participatingTeams;
    this.groups = groups;
  }

  public void addTeam(Team team) {
    this.participatingTeams.add(team);
    team.getPlayedDoublesTournaments().add(this);
  }

  public void removeTeam(Team team) {
    this.participatingTeams.remove(team);
    team.getPlayedDoublesTournaments().remove(this);
  }

  public void addGroup(GroupInDoubles group) {
    this.groups.add(group);
    // group.setPartOfDoublesTournament(this); no it is done in the group constructor
  }

  public void removeGroup(GroupInDoubles group) {
    this.groups.remove(groups);
    group.setPartOfDoublesTournament(null);
  }
}
