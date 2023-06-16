package com.app.tmbe.dataModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;

  @Column(name = "is_checked")
  private Boolean isChecked;

  @Column(name = "playerOneId")
  private long playerOneId;

  @Column(name = "playerTwoId")
  private long playerTwoId;

  @Column(name = "strength")
  private int strength;

  @Column(name = "comment")
  private String comment;

  // Bidirectional @ManyToMany, two parents, no children, one owner (Team)
  // The ownership of the relation is determined by the mappedBy attribute. The entity that isn’t
  // the owner will have the mappedBy attribute => Team is the owning parent, DoublesTournament is
  // the
  // referencing side

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "team_doubles_tournament",
      joinColumns = {
        @JoinColumn(name = "team_id", referencedColumnName = "id"),
      },
      inverseJoinColumns = {
        @JoinColumn(name = "doubles_tournament_id", referencedColumnName = "id"),
      })
  private Set<DoublesTournament> playedDoublesTournaments = new HashSet<>();

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "team_group_in_doubles",
      joinColumns = {
        @JoinColumn(name = "team_id", referencedColumnName = "id"),
      },
      inverseJoinColumns = {
        @JoinColumn(name = "group_in_doubles_id", referencedColumnName = "id"),
      })
  private Set<GroupInDoubles> belongsToDoublesGroups = new HashSet<>();

  public void addDoublesTournament(DoublesTournament doublesTournament) {
    this.playedDoublesTournaments.add(doublesTournament);
    doublesTournament.getParticipatingTeams().add(this);
  }

  public void removeDoublesTournament(DoublesTournament doublesTournament) {
    this.playedDoublesTournaments.remove(doublesTournament);
    doublesTournament.getParticipatingTeams().remove(this);
  }

  public void joinGroup(GroupInDoubles group) {
    this.belongsToDoublesGroups.add(group);
  }

  public void leaveGroup(GroupInDoubles group) {
    this.belongsToDoublesGroups.remove(group);
  }

  @Override
  public String toString() {
    return "Team{"
        + "id="
        + id
        + ", isChecked="
        + isChecked
        + ", playerOneId="
        + playerOneId
        + ", playerTwoId="
        + playerTwoId
        + ", strength="
        + strength
        + ", comment='"
        + comment
        + '\''
        + ", playedDoublesTournaments="
        + playedDoublesTournaments.size()
        + ", belongsToDoublesGroups="
        + belongsToDoublesGroups.size()
        + '}';
  }
}
