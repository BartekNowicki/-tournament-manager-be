package com.app.tmbe.dataModel;

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

  @Column(name = "isChecked")
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

  private void addDoublesTournament(DoublesTournament doublesTournament) {
    this.playedDoublesTournaments.add(doublesTournament);
    doublesTournament.getParticipatingTeams().add(this);
  }

  public void removeDoublesTournament(DoublesTournament doublesTournament) {
    this.playedDoublesTournaments.remove(doublesTournament);
    doublesTournament.getParticipatingTeams().remove(this);
  }

  @Override
  public String toString() {
    return "Team{"
        + "id="
        + id
        + ", isChecked="
        + isChecked
        + ", player1="
        + playerOneId
        + ", player2="
        + playerTwoId
        + ", strength="
        + strength
        + ", comment="
        + comment
        + '\''
        + ", players="
        + playedDoublesTournaments.size()
        + '}';
  }
}
