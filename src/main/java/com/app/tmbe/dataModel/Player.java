package com.app.tmbe.dataModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "players")
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;

  @Column(name = "isChecked")
  private Boolean isChecked;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "strength")
  private int strength;

  @Column(name = "comment")
  private String comment;

  // Bidirectional @ManyToMany, two parents, no children, one owner (Player)
  // The ownership of the relation is determined by the mappedBy attribute. The entity that isn’t
  // the owner will have the mappedBy attribute => Player is the owning parent, Tournament is the
  // referencing side

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "player_tournament",
      joinColumns = {
        @JoinColumn(name = "player_id", referencedColumnName = "id"),
      },
      inverseJoinColumns = {
        @JoinColumn(name = "tournament_id", referencedColumnName = "id"),
      })
  private Set<Tournament> playedTournaments = new HashSet<>();

  public void addTournament(Tournament tournament) {
    this.playedTournaments.add(tournament);
    tournament.getParticipatingPlayers().add(this);
  }

  public void removeTournament(Tournament tournament) {
    this.playedTournaments.remove(tournament);
    tournament.getParticipatingPlayers().remove(this);
  }

  @Override
  public String toString() {
    return "Player{"
        + "id="
        + id
        + ", isChecked="
        + isChecked
        + ", firstName="
        + firstName
        + ", lastName="
        + lastName
        + ", strength="
        + strength
        + ", comment="
        + comment
        + '\''
        + ", players="
        + playedTournaments.size()
        + '}';
  }
}